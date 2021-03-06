<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Commercial
 *
 * @property int $id
 * @property int $idUser
 * @property int $numeroCni
 * @property int $numeroBadge
 * @property string $ville
 * @property string $quartier
 * @property string|null $imageProfil
 * @property string|null $zone
 * @property int $actif
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\User $user
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial query()
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereActif($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereIdUser($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereImageProfil($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereNumeroBadge($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereNumeroCni($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereQuartier($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereUpdatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereVille($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereZone($value)
 * @mixin \Eloquent
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Etablissement[] $etablissements
 * @property-read int|null $etablissements_count
 * @property int|null $idZone
 * @property string $sexe
 * @property int $age
 * @property string $tailleTshirt
 * @property string $diplome
 * @property string $whatsapp
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereAge($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereDiplome($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereIdZone($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereSexe($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereTailleTshirt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Commercial whereWhatsapp($value)
 */
class Commercial extends Model
{
    use HasFactory;

    protected $fillable = [
        "idUser", "numeroCni", "numeroBadge", "ville", "quartier", "imageProfil", "idZone", "actif", "sexe", "whatsapp", "diplome", "tailleTshirt", "age"
    ];

    public function user()
    {
        return $this->belongsTo(User::class, "idUser");
    }

    public function zone()
    {
        return $this->belongsTo(Zone::class, "idZone");
    }

    public function etablissements()
    {
        return $this->hasMany(Etablissement::class, "idCommercial");
    }
}
