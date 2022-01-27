<?php

namespace App\Http\Controllers\Api;

use App\Models\Batiment;
use App\Models\Commercial;
use Auth;
use DB;
use Illuminate\Http\Request;

class BatimentController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $batiments = Batiment::all();

        foreach ($batiments as $batiment) {
            $etablissements = $batiment->etablissements;

            $batiment["etablissements"] = $etablissements;

            foreach ($etablissements as $etablissement) {
                $souscategorie = $etablissement->sousCategories;
                $images = $etablissement->images;
                $horaires = $etablissement->horaires;
                $telephones = $etablissement->telephones;
                $commercial = Commercial::find($etablissement->commercial->id);

                $etablissement["nomCommercial"] = $commercial->user->name;

                foreach ($etablissement->sousCategories as $souscategorie) {

                    $souscategorie["logoUrl"] = $souscategorie->categorie->logoUrl;
                }
            }
        }

        return $this->sendResponse($batiments, 'Liste des Batiments');
    }

    public function getBatimentsNumber()
    {
        $batiments = Batiment::all();



        return $this->sendResponse((string)$batiments->count(), 'Nombre de Batiments');
    }

    public function getBatimentsGeojson()
    {
        $batiments = Batiment::all();
        $response = array();
        foreach ($batiments as $batiment) {



            $etablissements = $batiment->etablissements;

            $batiment["etablissements"] = $etablissements;

            foreach ($etablissements as $etablissement) {
                $souscategorie = $etablissement->sousCategories;
                $images = $etablissement->images;
                $horaires = $etablissement->horaires;
                $telephones = $etablissement->telephones;
                $commercial = Commercial::find($etablissement->commercial->id);

                $etablissement["nomCommercial"] = $commercial->user->name;

                foreach ($etablissement->sousCategories as $souscategorie) {

                    $souscategorie["logoUrl"] = $souscategorie->categorie->logoUrl;
                }
            }

            $geometry = [
                "type" => "Point",
                "coordinates" => [$batiment->longitude, $batiment->latitude]
            ];

            $response[] = [
                "type" => "Feature",
                "geometry" => $geometry,
                "properties" => $batiment
            ];
        }



        $geojson = [
            "type" => "FeatureCollection",
            "features" => $response
        ];

        return response()->json($geojson, 200);
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
                $request->validate([
                    'file' => 'mimes:png,jpg,jpeg|max:10000'
                ]);
                $input = $request->all();

                $batiment = Batiment::create($input);

                if ($request->file()) {
                    $fileName = time() . '_' . $request->file->getClientOriginalName();
                    $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $request->codeBatiment, $fileName, 'public');
                    $batiment->image = '/storage/' . $filePath;
                }

                $save = $batiment->save();

                DB::commit();

                if ($save) {
                    return $this->sendResponse($batiment, "Création du batiment reussie", 201);
                } else {
                    return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
                }
            } else {
                return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
            }
        } catch (\Exception $ex) {
            DB::rollback();
            return $this->sendError("Erreur de Création.", ['error' => $ex->getMessage()], 500);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Batiment  $batiment
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $batiment = Batiment::find($id);

        $etablissements = $batiment->etablissements;

        $batiment["etablissements"] = $etablissements;

        foreach ($etablissements as $etablissement) {
            $souscategorie = $etablissement->sousCategories;
            $images = $etablissement->images;
            $horaires = $etablissement->horaires;
            $telephones = $etablissement->telephones;
            $commercial = Commercial::find($etablissement->commercial->id);

            $etablissement["nomCommercial"] = $commercial->user->name;


            foreach ($etablissement->sousCategories as $souscategorie) {

                $souscategorie["logoUrl"] = $souscategorie->categorie->logoUrl;
            };
        }

        return $this->sendResponse($batiment, 'Batiment');
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Batiment  $batiment
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $batiment = Batiment::find($id);
            $request->validate([
                'file' => 'mimes:png,jpg,jpeg|max:10000'
            ]);

            $batiment->nom = $request->nom ?? $batiment->nom;
            $batiment->nombreNiveaux = $request->nombreNiveaux ?? $batiment->nombreNiveaux;
            $batiment->indication = $request->indication ?? $batiment->indication;
            $batiment->rue = $request->rue ?? $batiment->rue;
            $batiment->ville = $request->ville ?? $batiment->ville;
            $batiment->commune = $request->commune ?? $batiment->commune;
            $batiment->quartier = $request->quartier ?? $batiment->quartier;

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $batiment->codeBatiment, $fileName, 'public');
                $batiment->image = '/storage/' . $filePath;
            }

            $save = $batiment->save();



            if ($save) {
                return $this->sendResponse($batiment, "Update Success", 201);
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
     * @param  \App\Models\Batiment  $batiment
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $batiment = Batiment::find($id);

            $batiment->etablissements()->delete();

            $batimentDestroy =  Batiment::destroy($id);

            if ($batimentDestroy) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }
}
