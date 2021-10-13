from datetime import datetime
from pydantic import BaseModel
from typing import Optional


class Etablissements(BaseModel):
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
    class Config:
        orm_mode = True

    def __dict__(self):
        return {'nom': self.nom, 'rue': self.rue, 'indication_adresse': self.indication_adresse,
                'ville': self.ville, 'adresse': self.adresse, 'lon': self.lon, 'lat': self.lat,'description': self.description,
                'code_postal': self.code_postal, 'site_internet': self.site_internet,'id_sous_categorie': self.id_sous_categorie, 
                'id_commercial': self.id_commercial,'id_manager': self.id_manager, 'paid': self.paid,
                'created_at': datetime.strptime(self.created_at,  '%d/%m/%y %H:%M:%S'),
                'updated_at': datetime.strptime(self.updated_at,  '%d/%m/%y %H:%M:%S') 
                }