<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Password2
 *
 * @property int $id
 * @property string $phone
 * @property string $nom
 * @property string $password
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 query()
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 whereNom($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 wherePassword($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 wherePhone($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Password2 whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class Password2 extends Model
{
    use HasFactory;

    protected $fillable = [
        "password", "phone", "nom"
    ];
}
