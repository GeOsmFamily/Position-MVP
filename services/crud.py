# crud.py
from typing import List
from sqlalchemy.orm import Session
from exceptions import (
    EtablissementInfoException,
    EtablissementInfoInfoAlreadyExistError,
    EtablissementInfoNotFoundError
)

from models import (
    Etablissements
)

from schemas import (
    CreateAndUpdateEtablissements
)


#### etablissements ####
# Function to get list of etablissements info
def get_all_ets(session: Session, limit: int, offset: int) -> List[Etablissements]:
    return session.query(Etablissements).offset(offset).limit(limit).all()


# Function to  get info of a particular etablissement
def get_etablissement_info_by_id(session: Session, _id: int) -> Etablissements:
    ets_info = session.query(Etablissements).get(_id)

    if ets_info is None:
        raise EtablissementInfoNotFoundError

    return ets_info


# Function to add a new etablissement info to the database
def create_ets(session: Session, ets_info: CreateAndUpdateEtablissements) -> Etablissements:
    ets_details = session.query(Etablissements).filter(
            Etablissements.nom==ets_info.nom,
            Etablissements.rue==ets_info.rue,
            Etablissements.indication_adresse==ets_info.indication_adresse,
            Etablissements.ville==ets_info.ville,
            Etablissements.adresse==ets_info.adresse,
            Etablissements.lon==ets_info.lon,
            Etablissements.lat==ets_info.lat,
            Etablissements.description==ets_info.description,
            Etablissements.code_postal==ets_info.code_postal,
            Etablissements.site_internet==ets_info.site_internet,
            Etablissements.id_sous_categorie==ets_info.id_sous_categorie,
            Etablissements.id_commercial==ets_info.id_commercial,
            Etablissements.id_manager==ets_info.id_manager,
            Etablissements.paid==ets_info.paid,
            Etablissements.created_at==ets_info.created_at,
            Etablissements.updated_at==ets_info.updated_at
        ).first()
    if ets_details is not None:
        raise EtablissementInfoInfoAlreadyExistError

    new_ets_info = Etablissements(**ets_info.dict())
    session.add(new_ets_info)
    session.commit()
    session.refresh(new_ets_info)
    return new_ets_info


# Function to update details of the ets
def update_ets_info(session: Session, _id: int, info_update: CreateAndUpdateEtablissements) -> Etablissements:
    ets_info = get_etablissement_info_by_id(session, _id)

    if ets_info is None:
        raise EtablissementInfoNotFoundError

    ets_info.nom = info_update.nom
    ets_info.rue = info_update.rue
    ets_info.indication_adresse = info_update.indication_adresse
    ets_info.ville = info_update.ville
    ets_info.adresse = info_update.adresse
    ets_info.lon = info_update.lon
    ets_info.lat = info_update.lat
    ets_info.description=info_update.description
    ets_info.code_postal=info_update.code_postal
    ets_info.ets_site_internet=info_update.site_internet
    ets_info.id_sous_categorie=info_update.id_sous_categorie
    ets_info.id_commercial=info_update.id_commercial
    ets_info.id_manager=info_update.id_manager
    ets_info.paid=info_update.paid
    ets_info.created_at=info_update.created_at
    ets_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(ets_info)

    return ets_info


# Function to delete an ets info from the db
def delete_ets_info(session: Session, _id: int):
    ets_info = get_etablissement_info_by_id(session, _id)

    if ets_info is None:
        raise EtablissementInfoNotFoundError

    session.delete(ets_info)
    session.commit()

    return