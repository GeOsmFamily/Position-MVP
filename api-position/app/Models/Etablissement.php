<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

/**
 * App\Models\Etablissement
 *
 * @property int $id
 * @property int $idBatiment
 * @property string $nom
 * @property string|null $indicationAdresse
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
 * @property string|null $description
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereDescription($value)
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\SousCategorie[] $sousCategories
 * @property-read int|null $sous_categories_count
 * @property string|null $autres
 * @property int $revoir
 * @property int $valide
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereAutres($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereRevoir($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereValide($value)
 * @property \Illuminate\Support\Carbon|null $deleted_at
 * @property string|null $osmId
 * @property int $updated
 * @method static \Illuminate\Database\Query\Builder|Etablissement onlyTrashed()
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereDeletedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereOsmId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Etablissement whereUpdated($value)
 * @method static \Illuminate\Database\Query\Builder|Etablissement withTrashed()
 * @method static \Illuminate\Database\Query\Builder|Etablissement withoutTrashed()
 */
class Etablissement extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = [
        "idBatiment", "nom", "indicationAdresse", "codePostal", "siteInternet", "description", "idCommercial", "idManager", "etage", "cover", "vues", "autres", "revoir", "valide", "osmId", "updated"
    ];

    public function batiment()
    {
        return $this->belongsTo(Batiment::class, "idBatiment");
    }

    public function sousCategories()
    {
        return $this->belongsToMany(SousCategorie::class, "souscategories_etablissements",  "idEtablissement", "idSousCategorie");
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
