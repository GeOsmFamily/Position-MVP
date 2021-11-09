<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Api\BaseController;
use App\Models\Manager;
use App\Models\User;
use App\Notifications\SendEmailParams;
use Auth;
use Hash;
use Illuminate\Http\Request;

class ManagerController extends BaseController
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
            $managers = Manager::all();
            foreach ($managers as $manager) {
                $user = User::find($manager->idUser);
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['role'] = $user->role;
                $result['idUser'] = $manager->idUser;
                $result['id'] = $manager->id;

                $data[] = $result;
            }

            return $this->sendResponse($data, 'Liste des Managers');
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
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
            try {
                $input['name'] = $request->name;
                $input['email'] = $request->email;
                $input['phone'] = $request->phone;
                $input['role'] = 3;
                $string = $this->randomString();
                $input['password'] = Hash::make($string);

                $userNew = User::create($input);

                $userNew->notify(new SendEmailParams($userNew->phone, $string));

                $inputManager['idUser'] = $userNew->id;

                $manager = $userNew->manager()->create($inputManager);



                if ($manager) {
                    return $this->sendResponse($manager, "Création du manager reussie", 201);
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
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 3) {
            $manager = Manager::find($id);
            if ($role == 3) {
                if ($user->id == $manager->idUser) {
                    $user = User::find($manager->idUser);
                    $result['name'] = $user->name;
                    $result['email'] = $user->email;
                    $result['phone'] = $user->phone;
                    $result['role'] = $user->role;
                    $result['idUser'] = $manager->idUser;
                    $result['id'] = $manager->id;

                    return $this->sendResponse($result, "Manager");
                } else {
                    return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
                }
            } else {
                $user = User::find($manager->idUser);
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['role'] = $user->role;
                $result['idUser'] = $manager->idUser;
                $result['id'] = $manager->id;

                return $this->sendResponse($result, "Manager");
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1 || $role == 3) {
            $manager = Manager::find($id);
            if ($role == 3) {
                if ($user->id == $manager->idUser) {
                    $userUpdate = User::find($manager->idUser);
                    $userUpdate->name = $request->name ?? $userUpdate->name;
                    $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                    $userUpdate->save();
                } else {
                    return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
                }
            } else {
                $userUpdate = User::find($manager->idUser);
                $userUpdate->name = $request->name ?? $userUpdate->name;
                $userUpdate->phone = $request->phone ?? $userUpdate->phone;
                $userUpdate->save();
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $manager = Manager::findOrFail($id);


            User::destroy($manager->idUser);

            $managerDestroy = Manager::destroy($id);

            if ($managerDestroy) {
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
}
