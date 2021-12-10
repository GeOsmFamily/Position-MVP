<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Commercial;
use App\Models\Manager;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Password;
use Illuminate\Support\Facades\Validator;

class UserController extends BaseController
{
    /**
     * Register api
     *
     * @return \Illuminate\Http\Response
     */
    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'string|max:191',
            'email' => 'email|unique:users,email',
            'password' => 'string|between:6,20',
            'phone' => 'regex:/^[\+0-9]+$/',
        ]);


        if ($validator->fails()) {
            return $this->sendError('Erreur de paramètres.', $validator->errors());
        }

        $input = $request->all();
        $input['password'] = bcrypt($input['password']);
        $input['email_verified_at'] = now();
        $user = User::create($input);
        $user->sendEmailVerificationNotification();
        $success['token'] = $user->createToken('Position')->accessToken;
        $success['user'] = $user;

        if ($user) {
            return $this->sendResponse($success, 'Création réussie verifiez vos mails.');
        } else {
            return $this->sendError("Pas autorisé.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Login api
     *
     * @return \Illuminate\Http\Response
     */
    public function login(Request $request)
    {
        $credentials = $request->only(['email', 'phone', 'password']);

        if (Auth::attempt($credentials)) {
            $user = Auth::user();
            $success['token'] = $user->createToken('Position')->accessToken;
            $success['user'] = $user;

            return $this->sendResponse($success, 'Connexion réussie.');
        } else {
            return $this->sendError('Pas autorisé.', ['error' => 'Unauthorised']);
        }
    }

    public function logout()
    {
        $user = Auth::user();
        $token = $user->token();
        $token->revoke();

        return $this->sendResponse("", 'Deconnexion réussie.');
    }

    public function getUser()
    {
        $user = Auth::user();

        $commercial = $user->commercial;
        $manager = $user->manager;

        if ($user) {
            $success["user"] = $user;
            $success["user"]["commercial"] = $commercial;
            $success["user"]["manager"] = $manager;

            return $this->sendResponse($success, 'Utilisateur');
        } else {
            return $this->sendError('Pas autorisé.', ['error' => 'Unauthorised']);
        }
    }

    /* public function getUserById(Request $request)
    {
        $id = $request->id;
        $user = User::find($id);
        if ($user) {
            $success["user"] = $user;
            return $this->sendResponse($success, 'Utilisateur');
        } else {
            return $this->sendError('Pas autorisé.', ['error' => 'Unauthorised']);
        }
    }*/

    public function updateUser(Request $request)
    {
        $user = Auth::user();

        $user->name = $request->name ?? $user->name;
        $user->phone = $request->phone ?? $user->phone;


        $save = $user->save();

        if ($save) {
            $success["user"] = $user;
            return $this->sendResponse($success, "Utilisateur");
        } else {
            return $this->sendError("Echec de mise à jour", ['error' => 'Unauthorised']);
        }
    }

    public function forgot()
    {
        $credentials = request()->validate(['email' => 'required|email']);

        Password::sendResetLink($credentials);

        return $this->sendResponse("", "Un lien de reinitialisation vous a été envoyé par mail.");
    }

    public function reset()
    {
        $credentials = request()->validate([
            'email' => 'required|email',
            'token' => 'required|string',
            'password' => 'required|string|confirmed'
        ]);

        $reset_password_status = Password::reset($credentials, function ($user, $password) {
            $user->password = $password;
            $user->save();
        });

        if ($reset_password_status == Password::INVALID_TOKEN) {
            return $this->sendError("Invalid token provided", ['error' => 'Unauthorised']);
        }

        return $this->sendResponse("", "Password has been successfully changed");
    }
}
