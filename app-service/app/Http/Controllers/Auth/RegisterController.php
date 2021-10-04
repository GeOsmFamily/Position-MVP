<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Http\Requests\RecoveryRequest;
use App\Http\Requests\RecoveryUpdatePasswordRequest;
use App\Http\Requests\UserRequest;
use App\Models\Auth\User;
use App\Notifications\SendEmailRecovery;
use App\Notifications\SendEmailVerification;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;
use Symfony\Component\HttpFoundation\Response;
use Illuminate\Http\JsonResponse;


class RegisterController extends Controller
{

    public function create(UserRequest $request): JsonResponse
    {

        try {
            $email = null;

            DB::transaction(function () use ($request, &$email) {
                $user = User::create($request->all());
                if ($user && $user->hasVerifiedEmail() === false) {

                    $user->notify(new SendEmailVerification);

                    $email = response()->json(
                        ['message' => 'Access your email to verify your account'],
                        Response::HTTP_CREATED
                    );
                }

                /*  $email = $this->sendEmailVerification($user->email);

                if ($email->getStatusCode() !== 200) {
                    throw new \Exception(json_decode($email->getContent())->message);
                }*/
            });

            return $email;
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
        }
    }


    public function sendEmailVerification(string $email): JsonResponse
    {
        try {
            $user = User::byEmail($email)->first();

            if ($user && $user->hasVerifiedEmail() === false) {

                $user->notify(new SendEmailVerification);

                return response()->json(
                    ['message' => 'Access your email to verify your account'],
                    Response::HTTP_CREATED
                );
            }

            return response()->json(
                ['message' => 'Your account not exist or has already been verified previously'],
                Response::HTTP_BAD_REQUEST
            );
        } catch (\Exception $e) {
            return response()->json(['message' => 'Failed to send confirmation email. Details: ' . $e->getMessage()], Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }


    public function verification(Request $request)
    {
        if ($request->hasValidSignature()) {

            $user = User::byEmail($request->user)->first();

            if (!$user)
                $user = User::byPhone($request->user)->first();

            if ($user && $user->hasVerifiedEmail() === false) {
                $user->markEmailAsVerified();
                return response()->json(['message' => 'Account successfully verified'], Response::HTTP_OK);
            }
        }

        return response()->json(['message' => 'Link expired'], Response::HTTP_BAD_REQUEST);
    }


    public function recovery(RecoveryRequest $request, string $email)
    {
        if (($user = User::byEmail($email)->first()) && $request->url) {
            return $this->sendEmailRecovery($user, $request->url);
        }

        return response()->json(['message' => 'Not Found'], Response::HTTP_BAD_REQUEST);
    }


    private function sendEmailRecovery(User $user, string $url)
    {
        try {
            $token = sha1($user->toJson() . now() . Str::random(10));
            $url = "{$url}?token={$token}";

            $this->verification(new SendEmailRecovery($url));

            DB::table('password_resets')->updateOrInsert(['email' => $user->email], ['token' => $token, 'created_at' => now()]);

            return response()->json(['message' => 'Access your email to recovery your password'], Response::HTTP_CREATED);
        } catch (\Exception $e) {

            return response()->json(['message' => 'Internal error: ' . $e->getMessage()], Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }


    public function updatePassword(RecoveryUpdatePasswordRequest $request)
    {
        $password_reset = DB::table('password_resets')->where('token', $request->token);

        if ($email_user = $password_reset->first()) {
            if ($user = User::byEmail($email_user->email)->first()) {
                $user->password = $request->password;
                $user->save();
                $password_reset->delete();
                return response()->json(['message' => 'Password changed successfully'], Response::HTTP_OK);
            }
        }

        return response()->json(['message' => 'Link expired'], Response::HTTP_BAD_REQUEST);
    }
}
