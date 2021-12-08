# schemas.py
from pydantic import BaseModel
from typing import Optional, List
from datetime import datetime, time



#### managers schemas ####
# TO support creation and update APIs
class CreateAndUpdateManagers(BaseModel):
    id : Optional[int]
    idUser : int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Managers(CreateAndUpdateManagers):
    id : int
    class Config():
        orm_mode = True
# To support list Managers APISousManagers
class PaginatedManagersInfo(BaseModel):
    limit: int
    offset: int
    data: List[Managers]



#### Etablissements schemas ####
# TO support creation and update APIs
class CreateAndUpdateEtablissements(BaseModel):
    id : Optional[int] 
    nom : Optional[str]
    idBatiment: Optional[int] 
    indicationAdresse : Optional[str]
    codePostal : Optional[str]
    siteInternet : Optional[str]
    idSousCategorie: Optional[int] 
    idManager: Optional[int]
    idCommercial: Optional[int] 
    etage : Optional[int]
    autres: Optional[str]
    cover : Optional[str]
    vues : Optional[int] 
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    description: Optional[str]
class Etablissements(CreateAndUpdateEtablissements):
    id : int
    class Config():
        orm_mode = True
class PaginatedEtablissementInfo(BaseModel):
    limit: int
    offset: int
    data: List[Etablissements]



#### Telephones schemas ####
# TO support creation and update APIs
class CreateAndUpdateTelephones(BaseModel):
    id : Optional[int]
    idEtablissement : int
    numero : str
    whatsapp : str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    etablissemnt : List[Etablissements] = []
class Telephones(CreateAndUpdateTelephones):
    id : int
    class Config():
        orm_mode = True
class PaginatedTelephonesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Telephones]

#### Horaires schemas ####
# TO support creation and update APIs
class CreateAndUpdateHoraires(BaseModel):
    id : Optional[int]
    idEtablissement : int
    jour : str
    ouvert : int
    heureOuverture : Optional[time]
    heureFermeture: Optional[time]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    etablissement: List[Etablissements] = []
class Horaires(CreateAndUpdateHoraires):
    id : int
    class Config():
        orm_mode = True
class PaginatedHorairesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Horaires]
    
#### Images schemas ####
# TO support creation and update APIs
class CreateAndUpdateImages(BaseModel):
    id : Optional[int]
    idEtablissement : int
    imageUrl : str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    etablissement : List[Etablissements] = []
class Images(CreateAndUpdateImages):
    id : int
    class Config():
        orm_mode = True
class PaginatedImagesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Images]


#### FailedJobs schemas ####
# TO support creation and update APIs
class CreateAndUpdateFailedJobs(BaseModel):
    id : Optional[int] 
    uuid : int
    connection : int
    queue : str
    payload : str
    exception : str
    failed_at : Optional[datetime]
class FailedJobs(CreateAndUpdateFailedJobs):
    id : int

    class Config():
        orm_mode = True
class PaginatedFailedJobsInfo(BaseModel):
    limit: int
    offset: int
    data: List[FailedJobs]

#### commercials schemas ####
# TO support creation and update APIs
class CreateAndUpdateCommercials(BaseModel):
    id : Optional[int]
    idUser : int
    numeroCni : str
    numeroBadge : int
    ville : str
    quartier : str
    imageProfil : str
    idZone : str
    actif : int
    sexe = str
    whatsapp = str
    diplome = str
    tailleTshirt = str
    age = int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    etablissement: List[Etablissements] = []
# TO support list and get APIs
class Commercials(CreateAndUpdateCommercials):
    id : int
    class Config():
        orm_mode = True
# To support list Commercials APISousCommercials
class PaginatedCommercialsInfo(BaseModel):
    limit: int
    offset: int
    data: List[Commercials]

#### Sous categories schemas ####
# TO support creation and update APIs
class CreateAndUpdateSousCategories(BaseModel):
    id : Optional[int]
    nom : Optional[str]
    idCategorie : Optional[int]
    logoUrl : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    etablissement: List[Etablissements] = []
class SousCategories(CreateAndUpdateSousCategories):
    id : int
    class Config():
        orm_mode = True
class PaginatedSousCategoriesInfo(BaseModel):
    limit: int
    offset: int
    data: List[SousCategories]

#### categories schemas ####
# TO support creation and update APIs
class CreateAndUpdateCategories(BaseModel):
    id : Optional[int]
    nom : Optional[str]
    logoUrl : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    souscategories: List[SousCategories] = []
# TO support list and get APIs
class Categories(CreateAndUpdateCategories):
    id : int
    class Config():
        orm_mode = True
# To support list Etablissement APISousCategories
class PaginatedCategoriesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Categories]


#### batiment schemas ####
# TO support creation and update APIs
class CreateAndUpdateBatiments(BaseModel):
    id : Optional[int]    
    nom : str
    nombreNiveaux : int
    codeBatiment : str
    longitude : str
    latitude : str
    image : Optional[str]
    indication : str
    rue : str
    ville : str
    commune : str
    quartier : str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    etablissement: List[Etablissements] = [] 
# TO support list and get APIs
class Batiments(CreateAndUpdateBatiments):
    id : int
    class Config():
        orm_mode = True
# To support list Batiment APISousBatiment
class PaginatedBatimentsInfo(BaseModel):
    limit: int
    offset: int
    data: List[Batiments]
    
    
    
####  users schemas ####
class CreateAndUpdateUsers(BaseModel):
    id : Optional[int]    
    name : str
    email : str
    email_verified_at : Optional[datetime]
    password : str
    phone : str
    role : int
    remember_token : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Users(CreateAndUpdateUsers):
    id : int
    class Config():
        orm_mode = True
# To support list Batiment APISousBatiment
class PaginatedUsersInfo(BaseModel):
    limit: int
    offset: int
    data: List[Users]


#### trackings schemas ####
class CreateAndUpdateTrackings(BaseModel):
    id : Optional[int]    
    idUser : int
    longitude : Optional[str]
    latitude : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Trackings(CreateAndUpdateTrackings):
    id : int
    class Config():
        orm_mode = True
# To support list Batiment APISousBatiment
class PaginatedTrackingsInfo(BaseModel):
    limit: int
    offset: int
    data: List[Trackings]


#### Zones schemas ####
class CreateAndUpdateZones(BaseModel):
    id : Optional[int]    
    nom : int
    ville : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Zones(CreateAndUpdateZones):
    id : int
    class Config():
        orm_mode = True
# To support list Batiment APISousBatiment
class PaginateZonesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Zones]


#### souscategories_etablissements schemas ####
class CreateAndUpdateSousCategoriesEtablissements(BaseModel):
    id : Optional[int]    
    idEtablissement : int
    idSousCategorie : int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class SousCategoriesEtablissements(CreateAndUpdateSousCategoriesEtablissements):
    id : int
    class Config():
        orm_mode = True
# To support list Batiment APISousBatiment
class PaginateSousCategorieEtablissementsInfo(BaseModel):
    limit: int
    offset: int
    data: List[SousCategoriesEtablissements]
    