<?php

namespace App\Http\Controllers\Api;

use App\Models\Categorie;
use Auth;
use Illuminate\Http\Request;

class CategorieController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $categories = Categorie::all();

        foreach ($categories as   $categorie) {
            $sousCategories = $categorie->sousCategories;
            $categorie["sous_categories"] = $sousCategories;
        }

        return $this->sendResponse($categories, 'Liste des Categories');
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

            $categorie = Categorie::create($input);

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/categories/logos/' . $request->nom, $fileName, 'public');
                $categorie->logoUrl = '/storage/' . $filePath;
            }

            $save = $categorie->save();



            if ($save) {
                return $this->sendResponse($categorie, "Création de la categorie reussie", 201);
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
     * @param  \App\Models\Categorie  $categorie
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $categorie = Categorie::find($id);

        $sousCategories = $categorie->sousCategories;

        $categorie["sous_categories"] = $sousCategories;

        return $this->sendResponse($categorie, 'Categorie');
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Categorie  $categorie
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $categorie = Categorie::find($id);
            $request->validate([
                'file' => 'mimes:png,svg,jpg,jpeg|max:10000'
            ]);

            $categorie->nom = $request->nom ?? $categorie->nom;

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/categories/logos/' . $categorie->nom, $fileName, 'public');
                $categorie->logoUrl = '/storage/' . $filePath;
            }

            $save = $categorie->save();



            if ($save) {
                return $this->sendResponse($categorie, "Update Success", 201);
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
     * @param  \App\Models\Categorie  $categorie
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $categorie = Categorie::find($id);

            $categorie->sousCategories()->delete();

            $categorieDestroy =  Categorie::destroy($id);

            if ($categorieDestroy) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    public function searchCategorie(Request $request)
    {
        $q      = $request->input('q');
        $categories = Categorie::where('nom', 'LIKE', '%' . $q . '%')
            ->get();

        return $this->sendResponse($categories, 'Liste des Categories');
    }
}
