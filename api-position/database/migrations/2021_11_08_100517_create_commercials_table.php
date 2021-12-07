<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateCommercialsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('commercials', function (Blueprint $table) {
            $table->id();
            $table->integer("idUser");
            $table->integer("numeroCni");
            $table->integer("numeroBadge");
            $table->string('ville');
            $table->string('quartier');
            $table->string('imageProfil')->nullable();
            $table->integer('idZone');
            $table->boolean('actif')->default(true);
            $table->string('sexe');
            $table->string('whatsapp');
            $table->string('diplome');
            $table->string('tailleTshirt');
            $table->integer('age');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('commercials');
    }
}
