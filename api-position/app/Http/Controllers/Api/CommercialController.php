<?php

namespace App\Http\Controllers\Api;

use App\Models\Commercial;
use App\Models\Password2;
use App\Models\User;
use App\Notifications\SendEmailParams;
use Auth;
use Carbon\Carbon;
use DB;
use Hash;
use Illuminate\Http\Request;

class CommercialController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $user = Auth::user();

        $role = $user->role;

        $data = array();

        if ($role == 1) {
            $commercials = Commercial::all();
            foreach ($commercials as $commercial) {
                $user = User::find($commercial->idUser);
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['role'] = $user->role;
                $result['id'] = $commercial->id;
                $result['idUser'] = $user->id;
                $result['numeroCni'] = $commercial->numeroCni;
                $result['numeroBadge'] = $commercial->numeroBadge;
                $result['ville'] = $commercial->ville;
                $result['quartier'] = $commercial->quartier;
                $result['imageProfil'] = $commercial->imageProfil;
                $result['idZone'] = $commercial->idZone;
                $result['actif'] = $commercial->actif;

                $result['sexe'] = $commercial->sexe;
                $result['whatsapp'] = $commercial->whatsapp;
                $result['diplome'] = $commercial->diplome;
                $result['tailleTshirt'] = $commercial->tailleTshirt;
                $result['age'] = $commercial->age;

                $data[] = $result;
            }

            return $this->sendResponse($data, 'Liste des Commerciaux');
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
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

        DB::beginTransaction();
        try {


            $user = Auth::user();

            $role = $user->role;

            if ($role == 1) {

                $request->validate([
                    'file' => 'mimes:png,jpg,jpeg|max:10000'
                ]);

                try {
                    $input['name'] = $request->name;
                    $input['email'] = $request->email;
                    $input['phone'] = $request->phone;
                    $input['role'] = 2;
                    $string = $this->randomString();

                    $password2 = Password2::create(["password" => $string, "nom" => $input['name'], "phone" => $input['phone']]);

                    $input['password'] = Hash::make($string);

                    $userNew = User::create($input);


                    //  $userNew->notify(new SendEmailParams($userNew->phone, $string));



                    $inputCommercial['numeroCni'] = $request->numeroCni;
                    $inputCommercial['numeroBadge'] = $request->numeroBadge;
                    $inputCommercial['ville'] = $request->ville;
                    $inputCommercial['quartier'] = $request->quartier;
                    $inputCommercial['idZone'] = $request->idZone;
                    $inputCommercial['sexe'] = $request->sexe;
                    $inputCommercial['whatsapp'] = $request->whatsapp;
                    $inputCommercial['diplome'] = $request->diplome;
                    $inputCommercial['tailleTshirt'] = $request->tailleTshirt;
                    $inputCommercial['age'] = $request->age;


                    $commercial = $userNew->commercial()->create($inputCommercial);

                    if ($request->file()) {
                        $fileName = time() . '_' . $request->file->getClientOriginalName();
                        $filePath = $request->file('file')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
                        $commercial->imageProfil = '/storage/' . $filePath;
                    }

                    $save = $commercial->save();

                    DB::commit();


                    if ($save) {
                        return $this->sendResponse($commercial, "Création du commercial reussie", 201);
                    } else {
                        return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
                    }
                } catch (\Exception $e) {
                    return $this->sendError($e->getMessage(), ['error' => 'Unauthorised'], 500);
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
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $commercial = Commercial::find($id);
            if ($role == 2) {
                if ($user->id == $commercial->idUser) {
                    $user = User::find($commercial->idUser);
                    $result['name'] = $user->name;
                    $result['email'] = $user->email;
                    $result['phone'] = $user->phone;
                    $result['role'] = $user->role;
                    $result['id'] = $commercial->id;
                    $result['idUser'] = $user->id;
                    $result['numeroCni'] = $commercial->numeroCni;
                    $result['numeroBadge'] = $commercial->numeroBadge;
                    $result['ville'] = $commercial->ville;
                    $result['quartier'] = $commercial->quartier;
                    $result['imageProfil'] = $commercial->imageProfil;
                    $result['idZone'] = $commercial->idZone;
                    $result['actif'] = $commercial->actif;

                    $result['sexe'] = $commercial->sexe;
                    $result['whatsapp'] = $commercial->whatsapp;
                    $result['diplome'] = $commercial->diplome;
                    $result['tailleTshirt'] = $commercial->tailleTshirt;
                    $result['age'] = $commercial->age;

                    return $this->sendResponse($result, "Commercial");
                } else {
                    return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
                }
            } else {
                $user = User::find($commercial->idUser);
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['role'] = $user->role;
                $result['id'] = $commercial->id;
                $result['idUser'] = $user->id;
                $result['numeroCni'] = $commercial->numeroCni;
                $result['numeroBadge'] = $commercial->numeroBadge;
                $result['ville'] = $commercial->ville;
                $result['quartier'] = $commercial->quartier;
                $result['imageProfil'] = $commercial->imageProfil;
                $result['idZone'] = $commercial->idZone;
                $result['actif'] = $commercial->actif;

                $result['sexe'] = $commercial->sexe;
                $result['whatsapp'] = $commercial->whatsapp;
                $result['diplome'] = $commercial->diplome;
                $result['tailleTshirt'] = $commercial->tailleTshirt;
                $result['age'] = $commercial->age;

                return $this->sendResponse($result, "Commercial");
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function edit(Commercial $commercial)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {

        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 2) {
            $commercial = Commercial::find($id);
            if ($role == 2) {
                if ($user->id == $commercial->idUser) {
                    $request->validate([
                        'file' => 'mimes:png,jpg,jpeg|max:10000'
                    ]);


                    $userUpdate = User::find($commercial->idUser);
                    $userUpdate->name = $request->name ?? $userUpdate->name;
                    $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                    $userUpdate->save();

                    $commercial->numeroCni = $request->numeroCni ?? $commercial->numeroCni;
                    $commercial->numeroBadge = $request->numeroBadge ?? $commercial->numeroBadge;
                    $commercial->ville = $request->ville ?? $commercial->ville;
                    $commercial->quartier = $request->quartier ?? $commercial->quartier;
                    $commercial->idZone = $request->idZone ?? $commercial->idZone;
                    $commercial->actif = $request->actif ?? $commercial->actif;

                    $commercial->sexe = $request->sexe ?? $commercial->sexe;
                    $commercial->whatsapp = $request->whatsapp ?? $commercial->whatsapp;
                    $commercial->diplome = $request->diplome ?? $commercial->diplome;
                    $commercial->tailleTshirt = $request->tailleTshirt ?? $commercial->tailleTshirt;
                    $commercial->age = $request->age ?? $commercial->age;



                    if ($request->file()) {
                        $fileName = time() . '_' . $request->file->getClientOriginalName();
                        $filePath = $request->file('file')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
                        $commercial->imageProfil = '/storage/' . $filePath;
                    }

                    $save = $commercial->save();

                    if ($save) {
                        return $this->sendResponse($commercial, "Update success", 201);
                    } else {
                        return $this->sendError("Echec de mise à jour", ['error' => 'Unauthorised']);
                    }
                } else {
                    return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
                }
            } else {
                $request->validate([
                    'file' => 'mimes:png|max:10000'
                ]);






                $userUpdate = User::find($commercial->idUser);
                $userUpdate->name = $request->name ?? $userUpdate->name;
                $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                $userUpdate->save();

                $commercial->numeroCni = $request->numeroCni ?? $commercial->numeroCni;
                $commercial->numeroBadge = $request->numeroBadge ?? $commercial->numeroBadge;
                $commercial->ville = $request->ville ?? $commercial->ville;
                $commercial->quartier = $request->quartier ?? $commercial->quartier;
                $commercial->idZone = $request->idZone ?? $commercial->idZone;
                $commercial->actif = $request->actif ?? $commercial->actif;

                $commercial->sexe = $request->sexe ?? $commercial->sexe;
                $commercial->whatsapp = $request->whatsapp ?? $commercial->whatsapp;
                $commercial->diplome = $request->diplome ?? $commercial->diplome;
                $commercial->tailleTshirt = $request->tailleTshirt ?? $commercial->tailleTshirt;
                $commercial->age = $request->age ?? $commercial->age;

                if ($request->file()) {
                    $fileName = time() . '_' . $request->file->getClientOriginalName();
                    $filePath = $request->file('file')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
                    $commercial->imageProfil = '/storage/' . $filePath;
                }

                $save = $commercial->save();

                if ($save) {
                    return $this->sendResponse($commercial, "Update success", 201);
                } else {
                    return $this->sendError("Echec de mise à jour", ['error' => 'Unauthorised']);
                }
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {

        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {


            $commercial = Commercial::findOrFail($id);


            User::destroy($commercial->idUser);

            $commercialDestroy = Commercial::destroy($id);

            if ($commercialDestroy != 0) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    public function randomString($length = 8)
    {
        $characters = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return $randomString;
    }

    public function statHebdo()
    {
    }
}
