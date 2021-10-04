<?php

namespace App\Http\Controllers;

use App\Models\Auth\Role;
use App\Models\Auth\User;
use App\Models\Manager;
use App\Notifications\SendEmailParams;
use DB;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;

class ManagerController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(): JsonResponse
    {
        try {
            $managers = Manager::all();
            foreach ($managers as $manager) {
                $user = User::find($manager->id_user);
                $result['name'] = $user->name;
                $result['email'] = $user->email;
                $result['phone'] = $user->phone;
                $result['idUser'] = $manager->id_user;
                $result['id'] = $manager->id;

                $data[] = $result;
            }

            return response()->json($data, Response::HTTP_OK);
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], Response::HTTP_BAD_REQUEST);
        }
    }

    public function getManagerById($id): JsonResponse
    {
        try {
            $manager = Manager::find($id);
            $user = User::find($manager->id_user);
            $manager['name'] = $user->name;
            $manager['email'] = $user->email;
            $manager['phone'] = $user->phone;

            return response()->json($manager, Response::HTTP_OK);
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
        try {
            $input['name'] = $request->name;
            $input['email'] = $request->email;
            $input['phone'] = $request->phone;
            $string = $this->randomString();
            $input['password'] = $string;

            $user = User::create($input);

            $user->notify(new SendEmailParams($user->phone, $string));

            $inputManager['id_user'] = $user->id;

            Manager::create($inputManager);


            $role_manager = Role::byName('manager');

            $insert = DB::table('role_user')->insert([
                [
                    'role_id' => $role_manager->id,
                    'user_id' => $user->id,
                ],
            ]);

            if ($insert == 1) {
                return  response()->json(
                    ['message' => 'Access your email to vrify account & check your params'],
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
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function show(Manager $manager)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function edit(Manager $manager)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Manager $manager)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Manager  $manager
     * @return \Illuminate\Http\Response
     */
    public function destroy($id): JsonResponse
    {
        try {
            $delete = Manager::destroy($id);
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
