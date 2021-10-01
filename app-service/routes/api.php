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

Route::post('register', [App\Http\Controllers\Auth\AuthController::class, 'register']);
Route::post('login', [App\Http\Controllers\Auth\AuthController::class, 'login']);

Route::get('logout', [App\Http\Controllers\Auth\AuthController::class, 'logout']);


Route::get('email/verify/{id}', [App\Http\Controllers\Auth\VerificationController::class, 'verify'])->name('verification.verify'); // Make sure to keep this as your route name

Route::get('email/resend', [App\Http\Controllers\Auth\VerificationController::class, 'resend'])->name('verification.resend');


Route::post('password/email', [App\Http\Controllers\Auth\AuthController::class, 'forgot']);

Route::post('password/reset', [App\Http\Controllers\Auth\AuthController::class, 'reset']);
