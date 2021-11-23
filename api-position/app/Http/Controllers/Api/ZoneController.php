<?php

namespace App\Http\Controllers\Api;

use App\Models\Zone;
use Auth;
use Illuminate\Http\Request;

class ZoneController extends BaseController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $zones = Zone::all();

        foreach ($zones as   $zone) {
            $commerciaux = $zone->commerciaux;
            $zone["commerciaux"] = $commerciaux;
        }

        return $this->sendResponse($zones, 'Liste des Zones');
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
            $input = $request->all();

            $zone = Zone::create($input);


            if ($zone) {
                return $this->sendResponse($zone, "Création de la zone reussie", 201);
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
     * @param  \App\Models\Zone  $zone
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $zone = Zone::find($id);

        $commerciaux = $zone->commerciaux;
        $zone["commerciaux"] = $commerciaux;


        return $this->sendResponse($zone, 'Zone');
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Zone  $zone
     * @return \Illuminate\Http\Response
     */
    public function edit(Zone $zone)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Zone  $zone
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {
            $zone = Zone::find($id);


            $zone->nom = $request->nom ?? $zone->nom;
            $zone->ville = $request->ville ?? $zone->ville;



            $save = $zone->save();



            if ($save) {
                return $this->sendResponse($zone, "Update Success", 201);
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
     * @param  \App\Models\Zone  $zone
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $user = Auth::user();

        $role = $user->role;

        if ($role == 1) {

            $zoneDestroy =  Zone::destroy($id);

            if ($zoneDestroy) {
                return $this->sendResponse("", "Suppression réussie");
            } else {
                return $this->sendError("Echec de Suppression", ['error' => 'Unauthorised']);
            }
        } else {
            return $this->sendError("Vous n'avez pas les droits.", ['error' => 'Unauthorised']);
        }
    }
}
