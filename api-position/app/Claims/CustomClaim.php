<?php

namespace App\Claims;

use App\Models\User;
use CorBosman\Passport\AccessToken;

class CustomClaim
{
    public function handle(AccessToken $token, $next)
    {
        $user = User::find($token->getUserIdentifier());

        $token->addClaim('user_id', $user->id);
        $token->addClaim('roles_id', [$user->role]);
        return $next($token);
    }
}
