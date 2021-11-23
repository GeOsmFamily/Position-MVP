<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Zone extends Model
{
    use HasFactory;

    protected $fillable = [
        "nom", "ville"
    ];

    public function commerciaux()
    {
        return $this->hasMany(Commercial::class, "idZone");
    }
}
