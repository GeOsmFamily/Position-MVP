<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

/**
 * App\Models\Image
 *
 * @property int $id
 * @property string $idEtablissement
 * @property string|null $imageUrl
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\Etablissement $etablissement
 * @method static \Illuminate\Database\Eloquent\Builder|Image newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Image newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Image query()
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereIdEtablissement($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereImageUrl($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereUpdatedAt($value)
 * @mixin \Eloquent
 * @property \Illuminate\Support\Carbon|null $deleted_at
 * @method static \Illuminate\Database\Query\Builder|Image onlyTrashed()
 * @method static \Illuminate\Database\Eloquent\Builder|Image whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|Image withTrashed()
 * @method static \Illuminate\Database\Query\Builder|Image withoutTrashed()
 */
class Image extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = [
        "idEtablissement", "imageUrl"
    ];

    public function etablissement()
    {
        return $this->belongsTo(Etablissement::class, "idEtablissement");
    }
}
