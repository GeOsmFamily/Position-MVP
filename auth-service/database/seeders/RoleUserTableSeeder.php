<?php
namespace Database\Seeders;
use Illuminate\Database\Seeder;
use App\Models\Auth\User;
use App\Models\Auth\Role;
use Illuminate\Support\Facades\DB;

class RoleUserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $me = User::byEmail('infos@geo.sm')->first();
        $role_admin = Role::byName('admin');

        DB::table('role_user')->insert([
            [
                'role_id' => $me->id,
                'user_id' => $role_admin->id,
            ],
        ]);
    }
}
