<?php

namespace Database\Seeders;

use DB;
use Illuminate\Database\Seeder;

class RolesTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('roles')->insert([
            [
                'name' => 'admin',
                'display_name' => 'Administrator',
                'description' => 'Administrator of system.',
                'created_at' => now(),
            ],

            [
                'name' => 'commercial',
                'display_name' => 'Commercial',
                'description' => 'Role de Commercial.',
                'created_at' => now(),
            ],

            [
                'name' => 'manager',
                'display_name' => 'Manager',
                'description' => 'Manager d\'une entreprise.',
                'created_at' => now(),
            ],
        ]);
    }
}
