<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Etablissements extends Migration
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
            $table->string('nom');
            $table->string('rue');
            $table->string('indication_adresse');
            $table->string('ville');
            $table->string('adresse');
            $table->string('lon');
            $table->string('lat');
            $table->string('description')->nullable();
            $table->string('code_postal')->nullable();
            $table->string('site_internet')->nullable();
            $table->integer('id_sous_categorie');
            $table->integer('id_commercial');
            $table->integer('id_manager');
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
