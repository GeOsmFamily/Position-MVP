<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

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
 */
class Telephone extends Model
{
    use HasFactory;

    protected $fillable = [
        "idEtablissement", "numero", "whatsapp"
    ];

    public function etablissement()
    {
        return $this->belongsTo(Etablissement::class, "idEtablissement");
    }
}
