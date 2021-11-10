<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class VerificationController extends BaseController
{
    public function verify($id, Request $request)
    {
        if (!$request->hasValidSignature()) {
            return $this->sendError("Invalid/Expired url provided.", ['error' => 'Unauthorised']);
        }

        $user = User::findOrFail($id);

        if (!$user->hasVerifiedEmail()) {
            $user->markEmailAsVerified();
        }
        $success['user'] = $user;
        return $this->sendResponse($success, 'Vérification Reussie');
    }

    public function resend()
    {
        if (Auth::user()->hasVerifiedEmail()) {
            return $this->sendError("Email already verified.", ['error' => 'Unauthorised'], 400);
        }

        Auth::user()->sendEmailVerificationNotification();
        $success['user'] = auth()->user();
        return $this->sendResponse($success, 'Email verification link sent on your email id');
    }
}
