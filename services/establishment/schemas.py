from datetime import date
from pydantic import BaseModel


class Etablissements(BaseModel):
    id: int
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
    created_at : date
    updated_at : date

    class Config:
        orm_mode = True