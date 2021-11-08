# Position Api

New Admin Laravel for project Position

## Installation

```sh
$ git clone https://github.com/GeOsmFamily/Position-MVP.git
$ cd Position-MVP/api-position
$ composer install
$ cp .env.example .env
$ php artisan key:generate
$ php artisan migrate
$ php artisan passport:install
$ php artisan storage:link

```

## Documentation

### Allowed verbs

`GET`, `POST`, `PUT`, `PATCH` ou `DELETE`

### Required in the header of all requests

```
Content-Type: application/json
Accept: application/json
```

### Required hash in the Header of all requisitions that need to be authorized

Insert your generated hash

```
Authorization: Bearer YourAccessToken
```

### Authentication

[POST api/auth/login](http://127.0.0.1:8000/api/auth/login) - Login

Request

```
Content-Type: application/json
Accept: application/json
{
    "email": "admin@position.cm",
    "password": "secret"
}
```

Response

```
{
    "success": true,
    "data": {
        "token": "access Token",
        "user": {
            "id": 1,
            "name": "Admin",
            "email": "admin@position.cm",
            "email_verified_at": "2021-10-19T11:18:29.000000Z",
            "phone": 699999999,
            "role": 1,
            "created_at": "2021-10-19T11:17:48.000000Z",
            "updated_at": "2021-10-19T11:18:29.000000Z"
        }
    },
    "message": "Connexion réussie."
}
```

[POST api/auth/register](http://127.0.0.1:8000/api/auth/register) - Register

Request

```
Content-Type: application/json
Accept: application/json
{
    "email": "admin@position.cm",
    "password": "secret",
    "name" : "Admin",
    "phone" : "699999999"
}
```

Response

```
{
    "success": true,
    "data": {
        "token": "access Token",
        "user": {
            "id": 1,
            "name": "Admin",
            "email": "admin@position.cm",
            "email_verified_at": "2021-10-19T11:18:29.000000Z",
            "phone": 699999999,
            "role": 1,
            "created_at": "2021-10-19T11:17:48.000000Z",
            "updated_at": "2021-10-19T11:18:29.000000Z"
        }
    },
    "message": "Création réussie verifiez vos mails."
}

```

[GET api/auth/logout](http://127.0.0.1:8000/api/auth/logout) - Logout

Request

```
Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{

}
```

Response

```
{
    "success": true,
    "data": "",
    "message": "Deconnexion réussie."
}

```

[POST auth/password/forgot](http://127.0.0.1:8000/api/auth/password/forgot) - Forgot Password

Request

```
Content-Type: application/json
Accept: application/json
{
    "email" : "admin@position.cm"
}
```

Response

```
{
    "success": true,
    "data": "",
    "message": "Un lien de reinitialisation vous a été envoyé par mail."
}
```
