<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Souscategories_etablissement
 *
 * @property int $id
 * @property int $idEtablissement
 * @property int $idSousCategorie
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement query()
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement whereIdEtablissement($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement whereIdSousCategorie($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Souscategories_etablissement whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class Souscategories_etablissement extends Model
{
    use HasFactory;

    protected $fillable = [
        "idSousCategorie", "idEtablissement"
    ];
}
