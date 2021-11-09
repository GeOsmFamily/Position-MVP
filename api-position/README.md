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

## Authentication

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

[POST api/auth/password/forgot](http://127.0.0.1:8000/api/auth/password/forgot) - Forgot Password

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

## Commerciaux

[GET api/commercial](http://127.0.0.1:8000/api/commercial) - Commercial List

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
"success":true,
"data":[
{
"name":"Commercial",
"email":"bt@geo.sm",
"phone":"699999998",
"role":2,
"id":1,
"idUser":5,
"numeroCni":12345678,
"numeroBadge":12,
"ville":"Douala",
"quartier":"Yassa",
"imageProfil":null,
"zone":"Akwa"
}
],
"message":"Liste des Commerciaux"
}

```

[POST api/commercial](http://127.0.0.1:8000/api/commercial) - Commercial Add

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
"name":"Commercial",
"email":"bt@geo.sm",
"phone":"699999998",
"password":"secret",
"numeroCni":12345678,
"numeroBadge":12,
"ville":"Douala",
"quartier":"Yassa",
"imageProfil":null,
"zone":"Akwa"
}

```

Response

```

{
"success": true,
"data": {
"numeroCni": "12345678",
"numeroBadge": "12",
"ville": "Douala",
"quartier": "Yassa",
"zone": "Akwa",
"idUser": 6,
"updated_at": "2021-11-08T16:23:21.000000Z",
"created_at": "2021-11-08T16:23:21.000000Z",
"id": 2
},
"message": "Création du commercial reussie"
}

```

[GET api/commercial/{id}](http://127.0.0.1:8000/api/commercial) - Commercial by Id

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
"success":true,
"data":[
{
"name":"Commercial",
"email":"bt@geo.sm",
"phone":"699999998",
"role":2,
"id":1,
"idUser":5,
"numeroCni":12345678,
"numeroBadge":12,
"ville":"Douala",
"quartier":"Yassa",
"imageProfil":null,
"zone":"Akwa",
"actif":"1"
}
],
"message":"Commercial"
}

```

[PUT api/commercial/{id}](http://127.0.0.1:8000/api/commercial) - Update Commercial Field

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
"name":"Commercial",
"phone":"699999998",
"numeroCni":12345678,
"numeroBadge":12,
"ville":"Douala",
"quartier":"Yassa",
"imageProfil":null,
"zone":"Akwa",
"actif" : "0"
}

```

Response

```

{
"success": true,
"data": {
"id": 2,
"idUser": 6,
"numeroCni": 12345678,
"numeroBadge": 12,
"ville": "Douala",
"quartier": "Yassa",
"imageProfil": null,
"zone": "Akwa",
"actif": 1,
"created_at": "2021-11-08T16:23:21.000000Z",
"updated_at": "2021-11-08T16:23:21.000000Z"
},
"message": "Update success"
}

```

[DELETE api/commercial/{id}](http://127.0.0.1:8000/api/commercial) - Delete Commercial

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
"message": "Suppression réussie"
}

```

## Manager

[GET api/manager](http://127.0.0.1:8000/api/manager) - Manager List

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
    "success":true,
    "data":[
        {
            "name":"Manager",
            "email":"tchoukouahaboris@outlook.fr",
            "phone":"699999996",
            "role":3,
            "idUser":20,
            "id":1
        }
    ],
    "message":"Liste des Managers"
}

```

[POST api/manager](http://127.0.0.1:8000/api/manager) - Manager Add

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
    "name":"Manager",
    "email":"bt@geo.sm",
    "phone":"699999998",
    "password":"secret",
}

```

Response

```

{
"success": true,
"data": {
"idUser": 6,
"updated_at": "2021-11-08T16:23:21.000000Z",
"created_at": "2021-11-08T16:23:21.000000Z",
"id": 2
},
"message": "Création du Manager reussie"
}

```

[GET api/manager/{id}](http://127.0.0.1:8000/api/manager) - Manager by Id

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
"success":true,
"data":[
{
"name":"Manager",
"email":"bt@geo.sm",
"phone":"699999998",
"role":3,
"id":1,
"idUser":5
}
],
"message":"Manager"
}

```

[PUT api/manager/{id}](http://127.0.0.1:8000/api/manager) - Update Manager Field

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
"name":"Manager",
"phone":"699999998",
}

```

Response

```

{
"success": true,
"data": {
"id": 2,
"idUser": 6,
"created_at": "2021-11-08T16:23:21.000000Z",
"updated_at": "2021-11-08T16:23:21.000000Z"
},
"message": "Update success"
}

```

[DELETE api/manager/{id}](http://127.0.0.1:8000/api/manager) - Delete Manager

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
"message": "Suppression réussie"
}

```
