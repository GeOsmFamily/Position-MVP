# schemas.py
from pydantic import BaseModel
from typing import Optional, List
from datetime import datetime, time

#### Telephones schemas ####
# TO support creation and update APIs
class CreateAndUpdateTelephones(BaseModel):
    id: Optional[int]
    id_etablissement : int
    numero : str
    whatsapp : str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
class Telephones(CreateAndUpdateTelephones):
    id: int
    class Config():
        orm_mode = True
class PaginatedTelephonesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Telephones]

#### Horaires schemas ####
# TO support creation and update APIs
class CreateAndUpdateHoraires(BaseModel):
    id: Optional[int]
    id_etablissement : int
    jour : str
    ouvert : int
    heureOuverture : Optional[time]
    heureFermeture: Optional[time]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
class Horaires(CreateAndUpdateHoraires):
    id: int
    class Config():
        orm_mode = True
class PaginatedHorairesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Horaires]
    
#### Images schemas ####
# TO support creation and update APIs
class CreateAndUpdateImages(BaseModel):
    id: Optional[int]
    id_etablissement : int
    image_url : str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
class Images(CreateAndUpdateImages):
    id: int
    class Config():
        orm_mode = True
class PaginatedImagesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Images]

#### Etablissements schemas ####
# TO support creation and update APIs
class CreateAndUpdateEtablissements(BaseModel):
    id: Optional[int]
    nom : str
    rue : str
    indication_adresse : str
    ville : str
    adresse : str
    lon : str
    lat : str
    description : str
    code_postal : str
    site_internet : str
    id_sous_categorie : int
    id_commercial : int
    id_manager : int
    paid : int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    horaires: List[Horaires] = []
    images: List[Images] = []
    telephones: List[Telephones] = []
class Etablissement(CreateAndUpdateEtablissements):
    id: int

    class Config():
        orm_mode = True
class PaginatedEtablissementInfo(BaseModel):
    limit: int
    offset: int
    data: List[Etablissement]

    

#### Sous categories schemas ####
# TO support creation and update APIs
class CreateAndUpdateSousCategories(BaseModel):
    id: int
    nom : str
    id_categorie : int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
class SousCategories(CreateAndUpdateSousCategories):
    id: int
    class Config():
        orm_mode = True
class PaginatedSousCategoriesInfo(BaseModel):
    limit: int
    offset: int
    data: List[SousCategories]


#### categories schemas ####
# TO support creation and update APIs
class CreateAndUpdateCategories(BaseModel):
    id: int
    nom : str
    logo_url : Optional[str]
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
    
    sous_categories: List[SousCategories] = []
# TO support list and get APIs
class Categories(CreateAndUpdateCategories):
    id: int
    class Config():
        orm_mode = True
# To support list Etablissement APISousCategories
class PaginatedCategoriesInfo(BaseModel):
    limit: int
    offset: int
    data: List[Categories]



#### managers schemas ####
# TO support creation and update APIs
class CreateAndUpdateManagers(BaseModel):
    id: Optional[int]
    id_user : int
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Managers(CreateAndUpdateManagers):
    id: int
    class Config():
        orm_mode = True
# To support list Managers APISousManagers
class PaginatedManagersInfo(BaseModel):
    limit: int
    offset: int
    data: List[Managers]



#### commercials schemas ####
# TO support creation and update APIs
class CreateAndUpdateCommercials(BaseModel):
    id: Optional[int]
    revenu_total : int
    id_user : int
    nombre_etablissement : int
    numero_cni : str
    ville: str
    quartier: str
    image_profil: str
    created_at : Optional[datetime]
    updated_at : Optional[datetime]
# TO support list and get APIs
class Commercials(CreateAndUpdateCommercials):
    id: int
    class Config():
        orm_mode = True
# To support list Commercials APISousCommercials
class PaginatedCommercialsInfo(BaseModel):
    limit: int
    offset: int
    data: List[Commercials]





