<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Batiment
 *
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment query()
 * @mixin \Eloquent
 * @property int $id
 * @property string $nom
 * @property int $nombreNiveaux
 * @property string $codeBatiment
 * @property float $longitude
 * @property float $latitude
 * @property string|null $image
 * @property string|null $indication
 * @property string|null $rue
 * @property string $ville
 * @property string $commune
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereCodeBatiment($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereCommune($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereImage($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereIndication($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereLatitude($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereLongitude($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereNom($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereNombreNiveaux($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereRue($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereUpdatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereVille($value)
 * @property string $quartier
 * @property-read \Illuminate\Database\Eloquent\Collection|Batiment[] $etablissements
 * @property-read int|null $etablissements_count
 * @method static \Illuminate\Database\Eloquent\Builder|Batiment whereQuartier($value)
 */
class Batiment extends Model
{
    use HasFactory;

    protected $fillable = [
        "nom", "nombreNiveaux", "codeBatiment", "longitude", "latitude", "image", "indication", "rue", "ville", "commune", "quartier"
    ];

    public function etablissements()
    {
        return $this->hasMany(Etablissement::class, "idBatiment");
    }
}
