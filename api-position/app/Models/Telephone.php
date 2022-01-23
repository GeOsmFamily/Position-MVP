<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

/**
 * App\Models\Telephone
 *
 * @property int $id
 * @property int $idEtablissement
 * @property string $numero
 * @property int $whatsapp
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \App\Models\Etablissement $etablissement
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone query()
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereIdEtablissement($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereNumero($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereUpdatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereWhatsapp($value)
 * @mixin \Eloquent
 * @property int $principal
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone wherePrincipal($value)
 * @property \Illuminate\Support\Carbon|null $deleted_at
 * @method static \Illuminate\Database\Query\Builder|Telephone onlyTrashed()
 * @method static \Illuminate\Database\Eloquent\Builder|Telephone whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|Telephone withTrashed()
 * @method static \Illuminate\Database\Query\Builder|Telephone withoutTrashed()
 */
class Telephone extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = [
        "idEtablissement", "numero", "whatsapp", "principal"
    ];

    public function etablissement()
    {
        return $this->belongsTo(Etablissement::class, "idEtablissement");
    }
}
