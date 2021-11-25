<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Commercial extends Model
{
    protected $fillable = [
        'id_user',
        'revenu_total',
        'nombre_etablissement',
        'numero_cni',
        'ville',
        'quartier',
        'image_profil'
    ];
}
