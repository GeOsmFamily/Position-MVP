<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Manager
 *
 * @property int $id
 * @property int $idUser
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\User $user
 * @method static \Illuminate\Database\Eloquent\Builder|Manager newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Manager newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Manager query()
 * @method static \Illuminate\Database\Eloquent\Builder|Manager whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Manager whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Manager whereIdUser($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Manager whereUpdatedAt($value)
 * @mixin \Eloquent
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Etablissement[] $etablissements
 * @property-read int|null $etablissements_count
 */
class Manager extends Model
{
    use HasFactory;

    protected $fillable = [
        "idUser"
    ];

    public function user()
    {
        return $this->belongsTo(User::class, "idUser");
    }

    public function etablissements()
    {
        return $this->hasMany(Etablissement::class, "idManager");
    }
}
