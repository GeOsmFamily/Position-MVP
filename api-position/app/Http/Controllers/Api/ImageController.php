<?php

namespace App\Http\Controllers\Api;

use App\Models\Commercial;
use App\Models\Etablissement;
use App\Models\Image;
use Auth;
use DB;
use Illuminate\Http\Request;

class ImageController extends BaseController
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
            $request->validate([
                'file' => 'mimes:png,jpg,jpeg|max:10000'
            ]);
            $input = $request->all();

            $etablissement = Etablissement::find($request->idEtablissement);

            $batiment = $etablissement->batiment;

            $image = $etablissement->images()->create($input);

            if ($request->file()) {
                $fileName = time() . '_' . $request->file->getClientOriginalName();
                $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $batiment->codeBatiment . '/' . $etablissement->nom, $fileName, 'public');
                $image->imageUrl = '/storage/' . $filePath;
            }

            $save = $image->save();



            if ($save) {
                return $this->sendResponse($image, "Ajout de l'image reussi", 201);
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
     * @param  \App\Models\Image  $image
     * @return \Illuminate\Http\Response
     */
    public function show(Image $image)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Image  $image
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        DB::beginTransaction();
        try {

            $user = Auth::user();

            $role = $user->role;

            $image = Image::find($id);


            $idCommercial = $image->etablissement->idCommercial;

            $commercial = Commercial::find($idCommercial);

            $idUserCommercial = $commercial->idUser;

            if ($role == 1 || $user->id == $idUserCommercial) {

                $batiment = $image->etablissement->batiment;
                $etablissement = $image->etablissement;

                if ($request->file()) {
                    $fileName = time() . '_' . $request->file->getClientOriginalName();
                    $filePath = $request->file('file')->storeAs('uploads/batiments/images/' . $batiment->codeBatiment . '/' . $etablissement->nom, $fileName, 'public');
                    $image->imageUrl = '/storage/' . $filePath;
                }

                $save = $image->save();

                DB::commit();

                if ($save) {
                    return $this->sendResponse($image, "Update Success", 201);
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
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Image  $image
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $image = Image::destroy($id);

            if ($image != 0) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }
}
