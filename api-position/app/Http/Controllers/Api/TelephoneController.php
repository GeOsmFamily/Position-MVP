<?php

namespace App\Http\Controllers\Api;

use App\Models\Etablissement;
use App\Models\Telephone;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class TelephoneController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
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
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {

            $input = $request->all();

            $etablissement = Etablissement::find($request->idEtablissement);

            $telephone = $etablissement->telephones()->create($input);

            if ($telephone) {
                return $this->sendResponse($telephone, "Ajout du telephone reussi", 201);
            } else {
                return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
            }
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Telephone  $telephone
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Telephone  $telephone
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 3) {
            $telephone = Telephone::find($id);
            $input = $request->all();
            $save = $telephone->save($input);

            if ($save) {
                return $this->sendResponse($telephone, "Update Success", 201);
            } else {
                return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Telephone  $telephone
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 3) {
            $telephone = Telephone::destroy($id);

            if ($telephone != 0) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }
}