<?php

namespace App\Http\Controllers\Api;

use App\Models\Batiment;
use App\Models\Commercial;
use App\Models\Etablissement;
use Auth;
use Illuminate\Http\Request;

class EtablissementController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $etablissements = Etablissement::all();

        return $this->sendResponse($etablissements, 'Liste des Etablissements');
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
            $request->validate([
                'file' => 'mimes:png,svg,jpg,jpeg|max:10000'
            ]);
            $input = $request->all();

            $commercial = Commercial::where("idUser", $user->id)->first();

            $input['idCommercial'] = $commercial->id;

            $batiment = Batiment::find($request->idBatiment);

            $etablissement = $batiment->etablissements()->create($input);

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $batiment->codeBatiment . '/' . $request->nom, $fileName, 'public');
                $etablissement->cover = '/storage/' . $filePath;
            }

            $save = $etablissement->save();

            if ($save) {
                return $this->sendResponse($etablissement, "Création de l'etablissement reussie", 201);
            } else {
                return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Etablissement  $etablissement
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $etablissement = Etablissement::find($id);

        $images = $etablissement->images;
        $telephones = $etablissement->telephones;
        $horaires = $etablissement->horaires;

        $etablissement["images"] = $images;
        $etablissement["telephones"] = $telephones;
        $etablissement["horaires"] = $horaires;

        return $this->sendResponse($etablissement, 'Etablissement');
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Etablissement  $etablissement
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $etablissement = Etablissement::find($id);
            $batiment = Batiment::find($etablissement->idBatiment);
            $request->validate([
                'file' => 'mimes:png,svg,jpg,jpeg|max:10000'
            ]);

            $etablissement->nom = $request->nom ?? $etablissement->nom;
            $etablissement->indicationAdresse = $request->indicationAdresse ?? $etablissement->indicationAdresse;
            $etablissement->codePostal = $request->codePostal ?? $etablissement->codePostal;
            $etablissement->siteInternet = $request->siteInternet ?? $etablissement->siteInternet;
            $etablissement->description = $request->description ?? $etablissement->description;
            $etablissement->etage = $request->etage ?? $etablissement->etage;
            $etablissement->idManager = $request->idManager ?? $etablissement->idManager;
            $etablissement->vues = $request->vues ?? $etablissement->vues;


            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $batiment->codeBatiment . '/' . $etablissement->nom, $fileName, 'public');
                $etablissement->cover = '/storage/' . $filePath;
            }

            $save = $etablissement->save();



            if ($save) {
                return $this->sendResponse($etablissement, "Upload success", 201);
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
     * @param  \App\Models\Etablissement  $etablissement
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $etablissement = Etablissement::find($id);

            $etablissement->images()->delete();

            $etablissement->telephones()->delete();

            $etablissement->horaires()->delete();

            $etablissementDestroy =  Etablissement::destroy($id);

            if ($etablissementDestroy != 0) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    public function searchEtablissement(Request $request)
    {
        $q      = $request->input('q');
        $etablissements = Etablissement::where('nom', 'LIKE', '%' . $q . '%')
            ->orWhere('indicationAdresse', 'LIKE', '%' . $q . '%')
            ->get();

        foreach ($etablissements as $etablissement) {
            $batiment = $etablissement->batiment;
            $souscategorie = $etablissement->sousCategorie;


            $etablissement['batiment'] = $batiment;
            $etablissement['nomSousCategorie'] = $souscategorie->nom;

            $categorie = $souscategorie->categorie;

            $etablissement['nomCategorie'] = $categorie->nom;
            $etablissement['logo_url'] = $categorie->logo_url;
        }

        return $this->sendResponse($etablissements, 'Liste des Etablissements');
    }
}
