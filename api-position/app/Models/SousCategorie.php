<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\SousCategorie
 *
 * @property int $id
 * @property string $nom
 * @property int $idCategorie
 * @property string|null $logoUrl
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\Categorie $categorie
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie query()
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereIdCategorie($value)
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereLogoUrl($value)
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereNom($value)
 * @method static \Illuminate\Database\Eloquent\Builder|SousCategorie whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class SousCategorie extends Model
{
    use HasFactory;

    protected $fillable = [
        "nom", "idCategorie", "logoUrl"
    ];

    public function categorie()
    {
        return $this->belongsTo(Categorie::class, "idCategorie");
    }
}
