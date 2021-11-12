<?php

namespace App\Http\Controllers\Api;

use App\Models\Commercial;
use App\Models\User;
use App\Notifications\SendEmailParams;
use Auth;
use Carbon\Carbon;
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
                $result['zone'] = $commercial->zone;
                $result['actif'] = $commercial->actif;

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
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {

            $request->validate([
                'imageProfil' => 'mimes:png,jpg,jpeg|max:10000'
            ]);

            try {
                $input['name'] = $request->name;
                $input['email'] = $request->email;
                $input['phone'] = $request->phone;
                $input['role'] = 2;
                $string = $this->randomString();
                $input['password'] = Hash::make($string);

                $userNew = User::create($input);


                $userNew->notify(new SendEmailParams($userNew->phone, $string));



                $inputCommercial['numeroCni'] = $request->numeroCni;
                $inputCommercial['numeroBadge'] = $request->numeroBadge;
                $inputCommercial['ville'] = $request->ville;
                $inputCommercial['quartier'] = $request->quartier;
                $inputCommercial['zone'] = $request->zone;


                $commercial = $userNew->commercial()->create($inputCommercial);

                if ($request->file()) {
                    $fileName = time() . '_' . $request->imageProfil->getClientOriginalName();
                    $filePath = $request->file('imageProfil')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
                    $commercial->imageProfil = '/storage/' . $filePath;
                }

                $save = $commercial->save();



                if ($save) {
                    return $this->sendResponse($commercial, "Création du commercial reussie", 201);
                } else {
                    return $this->sendError("Erreur de Création.", ['error' => 'Unauthorised']);
                }
            } catch (\Exception $e) {
                return $this->sendError($e->getMessage(), ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
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

                    $result['actif'] = $commercial->actif;

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

                $result['actif'] = $commercial->actif;

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
                        'imageProfil' => 'mimes:png,jpg,jpeg|max:10000'
                    ]);






                    $userUpdate = User::find($commercial->idUser);
                    $userUpdate->name = $request->name ?? $userUpdate->name;
                    $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                    $userUpdate->save();

                    $commercial->numeroCni = $request->numeroCni ?? $commercial->numeroCni;
                    $commercial->numeroBadge = $request->numeroBadge ?? $commercial->numeroBadge;
                    $commercial->ville = $request->ville ?? $commercial->ville;
                    $commercial->quartier = $request->quartier ?? $commercial->quartier;
                    $commercial->zone = $request->zone ?? $commercial->zone;
                    $commercial->actif = $request->actif ?? $commercial->actif;

                    if ($request->file()) {
                        $fileName = time() . '_' . $request->imageProfil->getClientOriginalName();
                        $filePath = $request->file('imageProfil')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
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
                    'imageProfil' => 'mimes:png|max:10000'
                ]);






                $userUpdate = User::find($commercial->idUser);
                $userUpdate->name = $request->name ?? $userUpdate->name;
                $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                $userUpdate->save();

                $commercial->numeroCni = $request->numeroCni ?? $commercial->numeroCni;
                $commercial->numeroBadge = $request->numeroBadge ?? $commercial->numeroBadge;
                $commercial->ville = $request->ville ?? $commercial->ville;
                $commercial->quartier = $request->quartier ?? $commercial->quartier;
                $commercial->zone = $request->zone ?? $commercial->zone;
                $commercial->actif = $request->actif ?? $commercial->actif;

                if ($request->file()) {
                    $fileName = time() . '_' . $request->imageProfil->getClientOriginalName();
                    $filePath = $request->file('imageProfil')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
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
