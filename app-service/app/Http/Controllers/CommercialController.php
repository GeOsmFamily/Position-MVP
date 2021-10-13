<?php

namespace App\Http\Controllers;

use App\Models\Auth\Role;
use App\Models\Auth\User;
use App\Models\Commercial;
use App\Notifications\SendEmailParams;
use App\Notifications\SendEmailVerification;
use DB;
use Hash;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;

class CommercialController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(): JsonResponse
    {

        try {
            $commercials = Commercial::all();
            foreach ($commercials as $commercial) {
                $user = User::find($commercial->id_user);
                $result['revenu_total'] = $commercial->revenu_total;
                $result['nombre_etablissement'] = $commercial->nombre_etablissement;
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['idUser'] = $commercial->id_user;
                $result['id'] = $commercial->id;

                $data[] = $result;
            }

            return response()->json($data, Response::HTTP_OK);
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
        }
    }

    public function getCommercialById($id): JsonResponse
    {
        try {
            $commercial = Commercial::find($id);
            $user = User::find($commercial->id_user);
            $commercial['name'] = $user->name;
            $commercial['email'] = $user->email;
            $commercial['phone'] = $user->phone;

            return response()->json($commercial, Response::HTTP_OK);
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
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
    public function store(Request $request): JsonResponse
    {
        $request->validate([
            'image' => 'mimes:png|max:10000'
        ]);

        try {
            $input['name'] = $request->name;
            $input['email'] = $request->email;
            $input['phone'] = $request->phone;
            $string = $this->randomString();
            $input['password'] = $string;

            $user = User::create($input);

            $user->notify(new SendEmailParams($user->phone, $string));

            $inputCommercial['id_user'] = $user->id;
            $inputCommercial['numero_cni'] = $request->numero_cni;
            $inputCommercial['ville'] = $request->ville;
            $inputCommercial['quartier'] = $request->quartier;


            $commercial = Commercial::create($inputCommercial);

            if ($request->file()) {
                $fileName = time() . '_' . $request->image_profil->getClientOriginalName();
                $filePath = $request->file('image_profil')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
                $commercial->image_profil = '/storage/' . $filePath;
            }

            $commercial->save();


            $role_commercial = Role::byName('commercial');

            $insert = DB::table('role_user')->insert([
                [
                    'role_id' => $role_commercial->id,
                    'user_id' => $user->id,
                ],
            ]);

            if ($insert == 1) {
                return  response()->json(
                    ['message' => 'Access your email to verify account & check your params'],
                    Response::HTTP_CREATED
                );
            } else {
                return response()->json(['message' => "Insert role error"], Response::HTTP_BAD_REQUEST);
            }
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function show(Commercial $commercial)
    {
        //
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
    public function update(Request $request, $id): JsonResponse
    {
        $request->validate([
            'image' => 'mimes:png|max:10000'
        ]);

        $commercial = Commercial::find($id);

        $commercial->numero_cni = $request->numero_cni ?? $commercial->numero_cni;
        $commercial->ville = $request->ville ?? $commercial->ville;
        $commercial->quartier = $request->quartier ?? $commercial->quartier;

        if ($request->file()) {
            $fileName = time() . '_' . $request->image_profil->getClientOriginalName();
            $filePath = $request->file('image_profil')->storeAs('uploads/commerciaux/profils', $fileName, 'public');
            $commercial->image_profil = '/storage/' . $filePath;
        }

        $save = $commercial->save();

        if ($save) {
            return  response()->json(
                ['message' => 'Update success'],
                Response::HTTP_CREATED
            );
        } else {
            return response()->json(['message' => "Update error"], Response::HTTP_BAD_REQUEST);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Commercial  $commercial
     * @return \Illuminate\Http\Response
     */
    public function destroy($id): JsonResponse
    {
        try {
            $delete = Commercial::destroy($id);
            if ($delete == 1) {
                return response()->json(["message" => "delete success"], Response::HTTP_OK);
            } else {
                return response()->json(['message' => "delete error"], Response::HTTP_BAD_REQUEST);
            }
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
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
