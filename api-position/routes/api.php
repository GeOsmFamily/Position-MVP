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

Route::post('auth/register', [App\Http\Controllers\Api\UserController::class, 'register']);
Route::post('auth/login', [App\Http\Controllers\Api\UserController::class, 'login']);



Route::get('auth/email/verify/{id}', [App\Http\Controllers\Api\VerificationController::class, 'verify'])->name('verification.verify'); // Make sure to keep this as your route name
Route::get('auth/email/resend', [App\Http\Controllers\Api\VerificationController::class, 'resend'])->name('verification.resend');

Route::post('auth/password/forgot', [App\Http\Controllers\Api\UserController::class, 'forgot']);
Route::post('auth/password/reset', [App\Http\Controllers\Api\UserController::class, 'reset'])->name('password.reset');

Route::get('categories', [App\Http\Controllers\Api\CategorieController::class, 'index']);
Route::get('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'show']);

Route::get('souscategories', [App\Http\Controllers\Api\SousCategorieController::class, 'index']);
Route::get('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'show']);


Route::middleware('auth:api')->group(function () {
    Route::get('auth/logout', [App\Http\Controllers\Api\UserController::class, 'logout']);
    Route::post('user/update', [App\Http\Controllers\Api\UserController::class, 'updateUser']);
    Route::get('user/me', [App\Http\Controllers\Api\UserController::class, 'getUser']);

    Route::resource('commercial', App\Http\Controllers\Api\CommercialController::class);
    Route::resource('manager', App\Http\Controllers\Api\ManagerController::class);

    Route::post('categories', [App\Http\Controllers\Api\CategorieController::class, 'store']);
    Route::put('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'update']);
    Route::delete('categories/{id}', [App\Http\Controllers\Api\CategorieController::class, 'destroy']);

    Route::post('souscategories', [App\Http\Controllers\Api\SousCategorieController::class, 'store']);
    Route::put('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'update']);
    Route::delete('souscategories/{id}', [App\Http\Controllers\Api\SousCategorieController::class, 'destroy']);
});
