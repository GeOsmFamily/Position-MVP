<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Etablissement
 *
 * @property int $id
 * @property int $idBatiment
 * @property string $nom
 * @property string $indicationAdresse
 * @property string|null $codePostal
 * @property string|null $siteInternet
 * @property int $idSousCategorie
 * @property int $idCommercial
 * @property int $idManager
 * @property int $etage
 * @property string|null $cover
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\Batiment $batiment
 * @property-read \App\Models\Commercial $commercial
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Horaire[] $horaires
 * @property-read int|null $horaires_count
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Image[] $images
 * @property-read int|null $images_count
 * @property-read \App\Models\Manager $manager
 * @property-read \App\Models\SousCategorie $sousCategorie
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Telephone[] $telephones
 * @property-read int|null $telephones_count
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement query()
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereCodePostal($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereCover($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereEtage($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereIdBatiment($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereIdCommercial($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereIdManager($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereIdSousCategorie($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereIndicationAdresse($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereNom($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereSiteInternet($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereUpdatedAt($value)
 * @mixin \Eloquent
 * @property int $vues
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereVues($value)
 */
class Etablissement extends Model
{
    use HasFactory;

    protected $fillable = [
        "idBatiment", "nom", "indicationAdresse", "codePostal", "siteInternet", "idSousCategorie", "idCommercial", "idManager", "etage", "cover", "vues"
    ];

    public function batiment()
    {
        return $this->belongsTo(Batiment::class, "idBatiment");
    }

    public function sousCategorie()
    {
        return $this->belongsTo(SousCategorie::class, "idSousCategorie");
    }

    public function commercial()
    {
        return $this->belongsTo(Commercial::class, "idCommercial");
    }

    public function manager()
    {
        return $this->belongsTo(Manager::class, "idManager");
    }



    public function images()
    {
        return $this->hasMany(Image::class, "idEtablissement");
    }

    public function telephones()
    {
        return $this->hasMany(Telephone::class, "idEtablissement");
    }

    public function horaires()
    {
        return $this->hasMany(Horaire::class, "idEtablissement");
    }
}