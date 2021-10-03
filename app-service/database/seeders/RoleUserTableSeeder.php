<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Auth\User;
use App\Models\Auth\Role;
use DB;

class RoleUserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $me = User::byPhone('699999999')->first();
        $role_admin = Role::byName('admin');

        DB::table('role_user')->insert([
            [
                'role_id' => $role_admin->id,
                'user_id' => $me->id,
            ],
        ]);
    }
}
