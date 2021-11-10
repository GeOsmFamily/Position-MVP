# Position Api

New Api Laravel for project Position

## Installation

```sh
$ git clone https://github.com/GeOsmFamily/Position-MVP.git
$ cd Position-MVP/api-position
```

-   edit docker-compose.yml and add Password for Mysql

```sh
MYSQL_ROOT_PASSWORD=
MYSQL_DATABASE=
```

-   add port_api & port_db in docker-compose.yml

```
port_api:80
"port_db:3306"
```

```
$ docker-compose up -d
$ docker exec -it api-position bash
$ composer install
$ cp .env.example .env
```

-   edit & add DB & Email infos in .env

```
DB_DATABASE=database name
DB_USERNAME=database username
DB_PASSWORD=database password
MAIL_MAILER=smtp
MAIL_HOST=your host
MAIL_PORT=your port
MAIL_USERNAME=your username
MAIL_PASSWORD=your password
MAIL_ENCRYPTION=TLS
MAIL_FROM_ADDRESS=infos@position.cm
MAIL_FROM_NAME=Position
```

```
$ php artisan key:generate
$ php artisan migrate
$ php artisan passport:install
$ php artisan db:seed
$ php artisan storage:link
$ exit
```

-   Add authorization in docker

```
go to position-api folder
$ chown -R www-data:www-data *
$ docker exec -it api-position-mysql bash
$ cd /var/lib
$ chown -R mysql:mysql mysql
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

## Catégories

[GET api/categories](http://127.0.0.1:8000/api/categories) - Categories List

Request

```

Content-Type: application/json
Accept: application/json
{

}

```

Response

```
{
    "success":true,
    "data":[
        {
            "id":1,
            "nom":"Achats",
            "logo_url":null,
            "created_at":"2021-11-09T17:54:43.000000Z",
            "updated_at":"2021-11-09T17:54:43.000000Z"
        },
            ...
    ],
    "message":"Liste des Categories"
}

```

[POST api/categories](http://127.0.0.1:8000/api/categories) - Categories Add

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
    "nom":"Categorie",
    "logo_url":"file",
}

```

Response

```

{
"success": true,
"data": {
            "id":1,
            "nom":"Achats",
            "logo_url":null,
            "created_at":"2021-11-09T17:54:43.000000Z",
            "updated_at":"2021-11-09T17:54:43.000000Z"
},
"message": "Création de la cotegorie reussie"
}

```

[GET api/categories/{id}](http://127.0.0.1:8000/api/categories) - Categorie by Id

Request

```

Content-Type: application/json
Accept: application/json
{

}

```

Response

```

{
    "success":true,
    "data":{
        "id":3,
        "nom":"Agriculture",
        "logo_url":null,
        "created_at":"2021-11-09T17:54:43.000000Z",
        "updated_at":"2021-11-09T17:54:43.000000Z",
        "sous_categories":[
            {
                "id":33,
                "nom":"Mat\u00e9riels et Produits agricoles",
                "idCategorie":3,
                "logoUrl":null,
                "created_at":"2021-11-10T11:55:55.000000Z",
                "updated_at":"2021-11-10T11:55:55.000000Z"
            },
            ....
        ]
    },
    "message":"Categorie"
}

```

[PUT api/categories/{id}](http://127.0.0.1:8000/api/categories) - Update Categorie Field

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
"nom":"Categorie",
"logo_url":"file",
}

```

Response

```

{
"success": true,
"data": {
            "id":1,
            "nom":"Achats",
            "logo_url":null,
            "created_at":"2021-11-09T17:54:43.000000Z",
            "updated_at":"2021-11-09T17:54:43.000000Z"
},
"message": "Update success"
}

```

[DELETE api/categories/{id}](http://127.0.0.1:8000/api/categories) - Delete Categorie

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

## Sous-Catégories

[GET api/souscategories](http://127.0.0.1:8000/api/souscategories) - Sous-Categories List

Request

```

Content-Type: application/json
Accept: application/json
{

}

```

Response

```
{
    "success":true,
    "data":[
        {
            "id": 1,
            "nom": "Boutiques",
            "idCategorie": 1,
            "logoUrl": null,
            "created_at": "2021-11-10T11:55:55.000000Z",
            "updated_at": "2021-11-10T11:55:55.000000Z"
        },
            ...
    ],
    "message":"Liste des Sous Categories"
}

```

[POST api/souscategories](http://127.0.0.1:8000/api/souscategories) - Sous Categories Add

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
    "nom":"Categorie",
    "logoUrl":"file",
    "idCategorie" : "1"
}

```

Response

```

{
"success": true,
"data": {
            "id": 1,
            "nom": "Boutiques",
            "idCategorie": 1,
            "logoUrl": null,
            "created_at": "2021-11-10T11:55:55.000000Z",
            "updated_at": "2021-11-10T11:55:55.000000Z"
},
"message": "Création de la sous categorie reussie"
}

```

[GET api/souscategories/{id}](http://127.0.0.1:8000/api/souscategories) - Sous-Categorie by Id

Request

```

Content-Type: application/json
Accept: application/json
{

}

```

Response

```

{
    "success":true,
    "data":{
        "id": 1,
        "nom": "Boutiques",
        "idCategorie": 1,
        "logoUrl": null,
        "created_at": "2021-11-10T11:55:55.000000Z",
        "updated_at": "2021-11-10T11:55:55.000000Z"
    },
    "message":"SousCategorie"
}

```

[PUT api/souscategories/{id}](http://127.0.0.1:8000/api/souscategories) - Update Sous Categorie Field

Request

```

Content-Type: application/json
Accept: application/json
Authorization: Bearer YourAccessToken
{
"nom":"SousCategorie",
"logoUrl":"file",
}

```

Response

```

{
"success": true,
"data": {
            "id": 1,
            "nom": "Boutiques",
            "idCategorie": 1,
            "logoUrl": null,
            "created_at": "2021-11-10T11:55:55.000000Z",
            "updated_at": "2021-11-10T11:55:55.000000Z"
},
"message": "Update success"
}

```

[DELETE api/souscategories/{id}](http://127.0.0.1:8000/api/souscategories) - Delete Sous-Categorie

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
