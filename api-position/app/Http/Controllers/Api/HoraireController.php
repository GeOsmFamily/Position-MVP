<?php

namespace App\Http\Controllers\Api;

use App\Models\Commercial;
use App\Models\Etablissement;
use App\Models\Horaire;
use Auth;
use DB;
use Illuminate\Http\Request;

class HoraireController extends BaseController
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

        DB::beginTransaction();
        try {


            $user = Auth::user();

            $role = $user->role;

            if ($role == 1 || $role == 2) {

                $input = $request->all();

                $etablissement = Etablissement::find($request->idEtablissement);

                $horaire = $etablissement->horaires()->create($input);

                DB::commit();

                if ($horaire) {
                    return $this->sendResponse($horaire, "Ajout de l'horaire reussi", 201);
                } else {
                    return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
                }
            }
        } catch (\Exception $ex) {
            DB::rollback();
            return $this->sendError("Erreur de Création.", ['error' => $ex->getMessage()], 500);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Horaire  $horaire
     * @return \Illuminate\Http\Response
     */
    public function show(Horaire $horaire)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Horaire  $horaire
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        $horaire = Horaire::find($id);

        $idCommercial = $horaire->etablissement->idCommercial;

        $commercial = Commercial::find($idCommercial);

        $idUserCommercial = $commercial->idUser;

        if ($role == 1 || $user->id == $idUserCommercial) {


            $horaire->heureOuverture = $request->heureOuverture ?? $horaire->heureOuverture;
            $horaire->heureFermeture = $request->heureFermeture ?? $horaire->heureFermeture;
            $save = $horaire->save();

            if ($save) {
                return $this->sendResponse($horaire, "Update Success", 201);
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
     * @param  \App\Models\Horaire  $horaire
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $horaire = Horaire::destroy($id);

            if ($horaire != 0) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }
}
