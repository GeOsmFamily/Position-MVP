# schemas.py
from pydantic import BaseModel
from typing import Optional, List
from datetime import datetime


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
    created_at : datetime
    updated_at : datetime
# TO support list and get APIs
class Etablissement(CreateAndUpdateEtablissements):
    id: int

    class Config():
        orm_mode = True
# To support list Etablissement API
class PaginatedEtablissementInfo(BaseModel):
    limit: int
    offset: int
    data: List[Etablissement]


