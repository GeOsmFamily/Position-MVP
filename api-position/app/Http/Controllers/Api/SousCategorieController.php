<?php

namespace App\Http\Controllers\Api;

use App\Models\SousCategorie;
use Auth;
use Illuminate\Http\Request;

class SousCategorieController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $souscategories = SousCategorie::all();

        return $this->sendResponse($souscategories, 'Liste des Sous-Categories');
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

        if ($role == 1) {
            $request->validate([
                'file' => 'mimes:png,svg|max:1000'
            ]);
            $input = $request->all();

            $souscategorie = SousCategorie::create($input);

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/sousCategories/logos/' . $request->nom, $fileName, 'public');
                $souscategorie->logoUrl = '/storage/' . $filePath;
            }

            $save = $souscategorie->save();



            if ($save) {
                return $this->sendResponse($souscategorie, "Création de la sous-categorie reussie", 201);
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
     * @param  \App\Models\SousCategorie  $sousCategorie
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $souscategorie = SousCategorie::find($id);


        return $this->sendResponse($souscategorie, 'SousCategorie');
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\SousCategorie  $sousCategorie
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $souscategorie = SousCategorie::find($id);
            $request->validate([
                'file' => 'mimes:png,svg|max:1000'
            ]);

            $souscategorie->nom = $request->nom ?? $souscategorie->nom;

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/sousCategories/logos/' . $request->nom, $fileName, 'public');
                $souscategorie->logoUrl = '/storage/' . $filePath;
            }

            $save = $souscategorie->save();



            if ($save) {
                return $this->sendResponse($souscategorie, "Update Success", 201);
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
     * @param  \App\Models\SousCategorie  $sousCategorie
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {

            $souscategorieDestroy =  SousCategorie::destroy($id);

            if ($souscategorieDestroy) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    public function searchSousCategorie(Request $request)
    {
        $q      = $request->input('q');
        $souscategories = SousCategorie::where('nom', 'LIKE', '%' . $q . '%')
            ->get();

        foreach ($souscategories as $souscategorie) {
            $etablissements = $souscategorie->etablissements;

            $souscategorie['etablissements'] = $etablissements;
            $categorie = $souscategorie->categorie;

            $souscategorie['nomCategorie'] = $categorie->nom;
            $souscategorie['logo_url'] = $categorie->logo_url;

            foreach ($etablissements as $etablissement) {
                $batiment = $etablissement->batiment;

                $etablissement['batiment'] = $batiment;
            }
        }





        return $this->sendResponse($souscategories, 'Liste des Sous-Categories');
    }
}
