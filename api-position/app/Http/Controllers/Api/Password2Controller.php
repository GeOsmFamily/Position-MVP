<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Api\BaseController;
use App\Models\Password2;
use Illuminate\Http\Request;

class Password2Controller extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $password2 = Password2::all();

        return $this->sendResponse($password2, 'Mots de passe');
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Password2  $password2
     * @return \Illuminate\Http\Response
     */
    public function show(Password2 $password2)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Password2  $password2
     * @return \Illuminate\Http\Response
     */
    public function edit(Password2 $password2)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Password2  $password2
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Password2 $password2)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Password2  $password2
     * @return \Illuminate\Http\Response
     */
    public function destroy(Password2 $password2)
    {
        //
    }
}
