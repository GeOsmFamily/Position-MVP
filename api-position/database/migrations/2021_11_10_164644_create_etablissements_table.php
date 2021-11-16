<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtablissementsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etablissements', function (Blueprint $table) {
            $table->id();
            $table->integer("idBatiment");
            $table->string('nom');
            $table->string('indicationAdresse');
            $table->string('codePostal')->nullable();
            $table->string('siteInternet')->nullable();
            $table->integer('idSousCategorie');
            $table->integer('idCommercial');
            $table->integer('idManager')->nullable();
            $table->integer('etage');
            $table->string('cover')->nullable();
            $table->integer('vues')->default(0);
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
        Schema::dropIfExists('etablissements');
    }
}
