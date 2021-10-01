<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::namespace('Auth')->name('auth.')->prefix('auth')->group(function() {

    /** Login **/
    Route::post('login', 'LoginController@login')->name('login');

    Route::middleware(['jwt.auth', 'verified'])->group(function() {
        Route::post('logout', 'LoginController@logout')->name('logout');
        Route::any('me', 'LoginController@me')->name('me');
    });

    Route::put('refresh', 'LoginController@refresh')->middleware('jwt.refresh')->name('refresh');

});






