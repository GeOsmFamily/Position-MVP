<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::get('auth/spassword', [App\Http\Controllers\Api\Password2Controller::class, 'index']);

Route::get('auth/email/verify/{id}', [App\Http\Controllers\Api\VerificationController::class, 'verify'])->name('verification.verify'); // Make sure to keep this as your route name
Route::get('auth/email/resend', [App\Http\Controllers\Api\VerificationController::class, 'resend'])->name('verification.resend');

Route::post('auth/password/forgot', [App\Http\Controllers\Api\UserController::class, 'forgot']);
Route::post('auth/password/reset', [App\Http\Controllers\Api\UserController::class, 'reset'])->name('password.reset');

Route::get('statistiques/global', [App\Http\Controllers\Api\StatistiquesController::class, 'getChiffreAffaire']);
Route::get('statistiques/today', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCurrentDay']);
Route::get('statistiques/thisweek', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCurrentWeek']);
Route::get('statistiques/thismonth', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCurrentMonth']);
Route::get('statistiques/byday', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByDay']);
Route::get('statistiques/commercial/{id}', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCommercial']);
Route::get('statistiques/commercial/day/{id}', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCommercialByDay']);
Route::get('statistiques/bysouscategories', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatBySousCategorie']);
Route::get('statistiques/bycategories', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatByCategorie']);
Route::get('statistiques/souscategories/{id}', [App\Http\Controllers\Api\StatistiquesController::class, 'getStatWithSousCategorie']);

Route::middleware('auth.apikey')->group(
    function () {

        Route::post('auth/register', [App\Http\Controllers\Api\UserController::class, 'register']);
        Route::post('auth/login', [App\Http\Controllers\Api\UserController::class, 'login']);





        Route::get('categories', [App\Http\Controllers\Api\CategorieController::class, 'index']);
        Route::get('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'show']);
        Route::get('search/categories', [App\Http\Controllers\Api\CategorieController::class, 'searchCategorie']);

        Route::get('souscategories', [App\Http\Controllers\Api\SousCategorieController::class, 'index']);
        Route::get('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'show']);
        Route::get('search/souscategories', [
            App\Http\Controllers\Api\SousCategorieController::class, 'searchSousCategorie'
        ]);

        Route::get('zones', [App\Http\Controllers\Api\ZoneController::class, 'index']);
        Route::get('zones/{id}', [App\Http\Controllers\Api\ZoneController::class, 'show']);



        Route::get('batiments', [App\Http\Controllers\Api\BatimentController::class, 'index']);
        Route::get('batimentsposition', [App\Http\Controllers\Api\BatimentController::class, 'getBatimentByPosition']);

        Route::get('batimentsgeojson', [App\Http\Controllers\Api\BatimentController::class, 'getBatimentsGeojson']);
        Route::get('batimentsnumber', [App\Http\Controllers\Api\BatimentController::class, 'getBatimentsNumber']);

        Route::get('batiments/{id}', [App\Http\Controllers\Api\BatimentController::class, 'show']);


        Route::get('etablissements', [App\Http\Controllers\Api\EtablissementController::class, 'index']);
        Route::get('etablissements/{id}', [App\Http\Controllers\Api\EtablissementController::class, 'show']);
        Route::get('search/etablissements', [App\Http\Controllers\Api\EtablissementController::class, 'searchEtablissement']);



        Route::middleware('auth:api')->group(function () {
            Route::get('auth/logout', [App\Http\Controllers\Api\UserController::class, 'logout']);
            Route::post('user/update', [App\Http\Controllers\Api\UserController::class, 'updateUser']);
            Route::get('user/me', [App\Http\Controllers\Api\UserController::class, 'getUser']);

            Route::apiResource('commercial', App\Http\Controllers\Api\CommercialController::class);
            Route::apiResource('manager', App\Http\Controllers\Api\ManagerController::class);

            Route::post('categories', [App\Http\Controllers\Api\CategorieController::class, 'store']);
            Route::put('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'update']);
            Route::delete('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'destroy']);


            Route::post('souscategories', [App\Http\Controllers\Api\SousCategorieController::class, 'store']);
            Route::put('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'update']);
            Route::delete('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'destroy']);

            Route::post('batiments', [App\Http\Controllers\Api\BatimentController::class, 'store']);
            Route::put('batiments/{id}', [App\Http\Controllers\Api\BatimentController::class, 'update']);
            Route::delete('batiments/{id}', [App\Http\Controllers\Api\BatimentController::class, 'destroy']);

            Route::post('etablissements', [App\Http\Controllers\Api\EtablissementController::class, 'store']);
            Route::put('etablissements/{id}', [App\Http\Controllers\Api\EtablissementController::class, 'update']);
            Route::delete('etablissements/{id}', [App\Http\Controllers\Api\EtablissementController::class, 'destroy']);

            Route::post('images', [App\Http\Controllers\Api\ImageController::class, 'store']);
            Route::put('images/{id}', [App\Http\Controllers\Api\ImageController::class, 'update']);
            Route::delete('images/{id}', [App\Http\Controllers\Api\ImageController::class, 'destroy']);

            Route::post('telephones', [App\Http\Controllers\Api\TelephoneController::class, 'store']);
            Route::put('telephones/{id}', [App\Http\Controllers\Api\TelephoneController::class, 'update']);
            Route::delete('telephones/{id}', [App\Http\Controllers\Api\TelephoneController::class, 'destroy']);

            Route::post('horaires', [App\Http\Controllers\Api\HoraireController::class, 'store']);
            Route::put('horaires/{id}', [App\Http\Controllers\Api\HoraireController::class, 'update']);
            Route::delete('horaires/{id}', [App\Http\Controllers\Api\HoraireController::class, 'destroy']);

            Route::apiResource('tracking', App\Http\Controllers\Api\TrackingController::class);

            Route::post('zones', [App\Http\Controllers\Api\ZoneController::class, 'store']);
            Route::put('zones/{id}', [App\Http\Controllers\Api\ZoneController::class, 'update']);
            Route::delete('zones/{id}', [App\Http\Controllers\Api\ZoneController::class, 'destroy']);
        });
    }
);
