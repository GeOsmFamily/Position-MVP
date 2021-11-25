<?php

namespace Database\Seeders;

use DB;
use Illuminate\Database\Seeder;

class SousCategoriesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $path = public_path('sql/sousCategories.sql');
        $sql = file_get_contents($path);
        DB::unprepared($sql);
    }
}
