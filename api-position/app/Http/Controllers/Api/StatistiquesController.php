<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Api\BaseController;
use App\Models\Commercial;
use App\Models\Etablissement;
use App\Models\Tracking;
use App\Models\User;
use Carbon\Carbon;
use Illuminate\Http\Request;

class StatistiquesController extends BaseController
{
    public function getChiffreAffaire()
    {
        $etablissements = Etablissement::all();

        $chiffre = $etablissements->count() * 1000;

        $idCommerciaux = array();
        foreach ($etablissements as $etablissement) {
            $idCommerciaux[] = $etablissement->idCommercial;
        }
        $commerciaux_actifs = count(array_count_values($idCommerciaux));

        $top_commercial = array_search(max(array_count_values($idCommerciaux)), array_count_values($idCommerciaux));

        $commercial = Commercial::find($top_commercial);
        $userCommercial = User::find($commercial->idUser);
        $etablissementsCommercial = Etablissement::where('idCommercial', $top_commercial)->get();

        $data["nombre_etablissement"] = $etablissements->count();
        $data["chiffre_affaire"] = $chiffre;
        $data["commerciaux_actifs"] = $commerciaux_actifs;
        $data["top_commercial"]["nom"] = $userCommercial->name;
        $data["top_commercial"]["nombre_etablissement"] = $etablissementsCommercial->count();
        $data["top_commercial"]["chiffre_affaire"] = $etablissementsCommercial->count() * 1000;
        $data["top_commercial"]["gain_personnel"] = ($etablissementsCommercial->count() * 1000 * 4) / 10;
        //  $data["top_commercial"]["etablissements"] = $etablissementsCommercial;

        return $this->sendResponse($data, "Chiffre d'affaire Total");
    }

    public function getStatByCurrentDay()
    {
        $etablissements = Etablissement::whereDate('created_at', date('Y-m-d'))->get();
        $etablissementsPreviousDay = Etablissement::whereDate('created_at', Carbon::yesterday()->format('Y-m-d'))->get();

        $idCommerciaux = array();
        foreach ($etablissements as $etablissement) {
            $idCommerciaux[] = $etablissement->idCommercial;
        }
        $commerciaux_actifs = count(array_count_values($idCommerciaux));

        $top_commercial = array_search(max(array_count_values($idCommerciaux)), array_count_values($idCommerciaux));

        $commercial = Commercial::find($top_commercial);
        $userCommercial = User::find($commercial->idUser);
        $etablissementsCommercial = Etablissement::where('idCommercial', $top_commercial)->whereDate('created_at', date('Y-m-d'))->get();


        $chiffre = $etablissements->count() * 1000;

        $data["jour"] = date('Y-m-d');
        $data["nombre_etablissement"] = $etablissements->count();
        $data["nombre_etablissement_yesterday"] = $etablissementsPreviousDay->count();
        $data["gain_jour"] = $chiffre;
        $data["commerciaux_actifs"] = $commerciaux_actifs;
        $data["top_commercial"]["nom"] = $userCommercial->name;
        $data["top_commercial"]["nombre_etablissement"] = $etablissementsCommercial->count();
        $data["top_commercial"]["chiffre_affaire"] = $etablissementsCommercial->count() * 1000;
        $data["top_commercial"]["gain_personnel"] = ($etablissementsCommercial->count() * 1000 * 4) / 10;
        $data["top_commercial"]["etablissements"] = $etablissementsCommercial;

        return $this->sendResponse($data, "Chiffre d'affaire Total");
    }

    public function getStatByDay(Request $request)
    {
        $day = $request->input('day');
        $etablissements = Etablissement::whereDate('created_at', $day)->get();

        $idCommerciaux = array();
        foreach ($etablissements as $etablissement) {
            $idCommerciaux[] = $etablissement->idCommercial;
        }
        $commerciaux_actifs = count(array_count_values($idCommerciaux));


        $top_commercial = array_search(max(array_count_values($idCommerciaux)), array_count_values($idCommerciaux));

        $commercial = Commercial::find($top_commercial);
        $userCommercial = User::find($commercial->idUser);
        $etablissementsCommercial = Etablissement::where('idCommercial', $top_commercial)->whereDate('created_at', $day)->get();


        $chiffre = $etablissements->count() * 1000;

        $data["jour"] = $day;
        $data["nombre_etablissement"] = $etablissements->count();
        $data["gain_jour"] = $chiffre;
        $data["commerciaux_actifs"] = $commerciaux_actifs;
        $data["top_commercial"]["nom"] = $userCommercial->name;
        $data["top_commercial"]["nombre_etablissement"] = $etablissementsCommercial->count();
        $data["top_commercial"]["chiffre_affaire"] = $etablissementsCommercial->count() * 1000;
        $data["top_commercial"]["gain_personnel"] = ($etablissementsCommercial->count() * 1000 * 4) / 10;
        $data["top_commercial"]["etablissements"] = $etablissementsCommercial;

        return $this->sendResponse($data, "Chiffre d'affaire Total");
    }

    public function getStatByCurrentWeek()
    {
        $etablissements = Etablissement::where('created_at', '>', Carbon::now()->startOfWeek())
            ->where('created_at', '<', Carbon::now()->endOfWeek())->get();

        $idCommerciaux = array();
        foreach ($etablissements as $etablissement) {
            $idCommerciaux[] = $etablissement->idCommercial;
        }
        $commerciaux_actifs = count(array_count_values($idCommerciaux));

        $top_commercial = array_search(max(array_count_values($idCommerciaux)), array_count_values($idCommerciaux));

        $commercial = Commercial::find($top_commercial);
        $userCommercial = User::find($commercial->idUser);
        $etablissementsCommercial = Etablissement::where('idCommercial', $top_commercial)->where('created_at', '>', Carbon::now()->startOfWeek())
            ->where('created_at', '<', Carbon::now()->endOfWeek())->get();


        $chiffre = $etablissements->count() * 1000;

        $data["week"] = "Du " . Carbon::now()->startOfWeek()->format('Y-m-d') . " au " . Carbon::now()->endOfWeek()->format('Y-m-d');
        $data["nombre_etablissement"] = $etablissements->count();
        $data["gain_jour"] = $chiffre;
        $data["commerciaux_actifs"] = $commerciaux_actifs;
        $data["top_commercial"]["nom"] = $userCommercial->name;
        $data["top_commercial"]["nombre_etablissement"] = $etablissementsCommercial->count();
        $data["top_commercial"]["chiffre_affaire"] = $etablissementsCommercial->count() * 1000;
        $data["top_commercial"]["gain_personnel"] = ($etablissementsCommercial->count() * 1000 * 4) / 10;
        $data["top_commercial"]["etablissements"] = $etablissementsCommercial;

        return $this->sendResponse($data, "Chiffre d'affaire Total");
    }

    public function getStatByCurrentMonth()
    {
        $etablissements = Etablissement::whereMonth('created_at', Carbon::now()->month)->whereYear('created_at', Carbon::now()->year)->get();

        $idCommerciaux = array();
        foreach ($etablissements as $etablissement) {
            $idCommerciaux[] = $etablissement->idCommercial;
        }
        $commerciaux_actifs = count(array_count_values($idCommerciaux));

        $top_commercial = array_search(max(array_count_values($idCommerciaux)), array_count_values($idCommerciaux));

        $commercial = Commercial::find($top_commercial);
        $userCommercial = User::find($commercial->idUser);
        $etablissementsCommercial = Etablissement::where('idCommercial', $top_commercial)->whereMonth('created_at', Carbon::now()->month)->whereYear('created_at', Carbon::now()->year)->get();


        $chiffre = $etablissements->count() * 1000;

        $data["mois"] = date('Y-m');
        $data["nombre_etablissement"] = $etablissements->count();
        $data["gain_jour"] = $chiffre;
        $data["commerciaux_actifs"] = $commerciaux_actifs;
        $data["top_commercial"]["nom"] = $userCommercial->name;
        $data["top_commercial"]["nombre_etablissement"] = $etablissementsCommercial->count();
        $data["top_commercial"]["chiffre_affaire"] = $etablissementsCommercial->count() * 1000;
        $data["top_commercial"]["gain_personnel"] = ($etablissementsCommercial->count() * 1000 * 4) / 10;
        $data["top_commercial"]["etablissements"] = $etablissementsCommercial;

        return $this->sendResponse($data, "Chiffre d'affaire Total");
    }

    public function getStatByCommercial($id)
    {
        $commercial = Commercial::find($id);

        $etablissementsToday = Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', date('Y-m-d'))->get();
        $etablissementsYesterday = Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', Carbon::yesterday()->format('Y-m-d'))->get();
        $etablissementsWeek = Etablissement::where('idCommercial', $commercial->id)->where('created_at', '>', Carbon::now()->startOfWeek())
            ->where('created_at', '<', Carbon::now()->endOfWeek())->get();
        $etablissementsMonth = Etablissement::where('idCommercial', $commercial->id)->whereMonth('created_at', Carbon::now()->month)->whereYear('created_at', Carbon::now()->year)->get();

        $userCommercial = User::find($commercial->idUser);

        $data["nbre_etablissement_today"] = $etablissementsToday->count();
        $data["chiffre_affaire"] = $etablissementsToday->count() * 1000;
        $data["gain_jour"] = ($etablissementsToday->count() * 1000 * 4) / 10;
        $data["nbre_etablissement_yesterday"] = $etablissementsYesterday->count();
        $data["nbre_etablissement_week"] = $etablissementsWeek->count();
        $data["nbre_etablissement_month"] = $etablissementsMonth->count();
        $data["gain_mensuel"] = ($etablissementsMonth->count() * 1000 * 4) / 10;
        $data["nom_commercial"] = $userCommercial->name;
        $trackings = Tracking::where('idUser', $userCommercial->id)->whereDate('created_at', date('Y-m-d'))->get();
        $data["trackings_today"] = $trackings;

        $monday = Carbon::now()->startOfWeek();
        $tuesday = $monday->copy()->addDay();
        $wednesday = $tuesday->copy()->addDay();
        $thursday = $wednesday->copy()->addDay();
        $friday = $thursday->copy()->addDay();
        $saturday = $friday->copy()->addDay();
        $sunday = $saturday->copy()->addDay();

        $mondayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $monday)->get()->count();
        $tuesdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $tuesday)->get()->count();
        $wednesdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $wednesday)->get()->count();
        $thursdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $thursday)->get()->count();
        $fridayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $friday)->get()->count();
        $saturdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $saturday)->get()->count();
        $sundayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $sunday)->get()->count();

        $data["week"]["period"] = "Du " . Carbon::now()->startOfWeek()->format('Y-m-d') . " au " . Carbon::now()->endOfWeek()->format('Y-m-d');
        $data["week"]["lundi"] = $mondayStat;
        $data["week"]["mardi"] = $tuesdayStat;
        $data["week"]["mercredi"] = $wednesdayStat;
        $data["week"]["jeudi"] = $thursdayStat;
        $data["week"]["vendredi"] = $fridayStat;
        $data["week"]["samedi"] = $saturdayStat;
        $data["week"]["dimanche"] = $sundayStat;

        $data["etablissement_today"] = $etablissementsToday;
        $data["etablissement_week"] = $etablissementsWeek;
        $data["etablissement_month"] = $etablissementsMonth;


        return $this->sendResponse($data, "Statistique Commercial");
    }


    public function getStatByCommercialByDay($id, Request $request)
    {
        $commercial = Commercial::find($id);
        $day = $request->input('day');

        $etablissementsToday = Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $day)->get();
        $etablissementsWeek = Etablissement::where('idCommercial', $commercial->id)->where('created_at', '>', Carbon::createFromFormat('Y-m-d', $day)->startOfWeek())
            ->where('created_at', '<', Carbon::createFromFormat('Y-m-d', $day)->endOfWeek())->get();
        $etablissementsMonth = Etablissement::where('idCommercial', $commercial->id)->whereMonth('created_at', Carbon::createFromFormat('Y-m-d', $day)->month)->whereYear('created_at', Carbon::createFromFormat('Y-m-d', $day)->year)->get();

        $userCommercial = User::find($commercial->idUser);

        $data["nbre_etablissement_today"] = $etablissementsToday->count();
        $data["chiffre_affaire"] = $etablissementsToday->count() * 1000;
        $data["gain_jour"] = ($etablissementsToday->count() * 1000 * 4) / 10;
        $data["nbre_etablissement_week"] = $etablissementsWeek->count();
        $data["nbre_etablissement_month"] = $etablissementsMonth->count();
        $data["gain_mensuel"] = ($etablissementsMonth->count() * 1000 * 4) / 10;
        $data["nom_commercial"] = $userCommercial->name;
        $trackings = Tracking::where('idUser', $userCommercial->id)->whereDate('created_at', $day)->get();
        $data["trackings_today"] = $trackings;

        $monday = Carbon::createFromFormat('Y-m-d', $day)->startOfWeek();
        $tuesday = $monday->copy()->addDay();
        $wednesday = $tuesday->copy()->addDay();
        $thursday = $wednesday->copy()->addDay();
        $friday = $thursday->copy()->addDay();
        $saturday = $friday->copy()->addDay();
        $sunday = $saturday->copy()->addDay();

        $mondayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $monday)->get()->count();
        $tuesdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $tuesday)->get()->count();
        $wednesdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $wednesday)->get()->count();
        $thursdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $thursday)->get()->count();
        $fridayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $friday)->get()->count();
        $saturdayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $saturday)->get()->count();
        $sundayStat =  Etablissement::where('idCommercial', $commercial->id)->whereDate('created_at', $sunday)->get()->count();

        $data["week"]["period"] = "Du " . Carbon::createFromFormat('Y-m-d', $day)->startOfWeek()->format('Y-m-d') . " au " . Carbon::createFromFormat('Y-m-d', $day)->endOfWeek()->format('Y-m-d');
        $data["week"]["lundi"] = $mondayStat;
        $data["week"]["mardi"] = $tuesdayStat;
        $data["week"]["mercredi"] = $wednesdayStat;
        $data["week"]["jeudi"] = $thursdayStat;
        $data["week"]["vendredi"] = $fridayStat;
        $data["week"]["samedi"] = $saturdayStat;
        $data["week"]["dimanche"] = $sundayStat;

        $data["etablissement_today"] = $etablissementsToday;
        $data["etablissement_week"] = $etablissementsWeek;
        $data["etablissement_month"] = $etablissementsMonth;


        return $this->sendResponse($data, "Statistique Commercial");
    }
}
