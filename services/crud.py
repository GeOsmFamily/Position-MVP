# crud.py
from typing import List
from sqlalchemy.orm import Session
from exceptions import (
    CategoriesInfoInfoAlreadyExistError,
    CategoriesInfoNotFoundError,
    EtablissementInfoException,
    EtablissementInfoInfoAlreadyExistError,
    EtablissementInfoNotFoundError,
    SousCategoriesInfoInfoAlreadyExistError,
    SousCategoriesInfoNotFoundError
)

from models import (
    Categories,
    Etablissements,
    SousCategories
)

from schemas import (
    CreateAndUpdateCategories,
    CreateAndUpdateEtablissements,
    CreateAndUpdateSousCategories
)


#### etablissements ####
# Function to get list of etablissements info
def get_all_ets(session: Session, limit: int, offset: int) -> List[Etablissements]:
    return session.query(Etablissements).offset(offset).limit(limit).all()

# Function to get list of etablissements info that paid or not
def get_all_ets_by_payment(session: Session, limit: int, offset: int, pay: int) -> List[Etablissements]:
    return session.query(Etablissements).filter_by(paid=int(pay)).offset(offset).limit(limit).all()


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







#### souscategories ####
# Function to get list of souscategories info
def get_all_souscategories(session: Session, limit: int, offset: int) -> List[SousCategories]:
    return session.query(SousCategories).offset(offset).limit(limit).all()

# Function to  get info of a particular souscategories
def get_souscategories_info_by_id(session: Session, _id: int) -> SousCategories:
    souscategories_info = session.query(SousCategories).get(_id)

    if souscategories_info is None:
        raise SousCategoriesInfoNotFoundError

    return souscategories_info


# Function to add a new souscategories info to the database
def create_souscategories(session: Session, ets_info: CreateAndUpdateSousCategories) -> SousCategories:
    souscategories_details = session.query(SousCategories).filter(
            SousCategories.nom==ets_info.nom,
            SousCategories.id_categorie==ets_info.id_categorie,
            SousCategories.created_at==ets_info.created_at,
            SousCategories.updated_at==ets_info.updated_at
        ).first()
    if souscategories_details is not None:
        raise SousCategoriesInfoInfoAlreadyExistError

    new_souscategories_info = SousCategories(**ets_info.dict())
    session.add(new_souscategories_info)
    session.commit()
    session.refresh(new_souscategories_info)
    return new_souscategories_info


# Function to update details of the souscategories
def update_souscategories_info(session: Session, _id: int, info_update: CreateAndUpdateSousCategories) -> SousCategories:
    souscategories_info = get_souscategories_info_by_id(session, _id)

    if souscategories_info is None:
        raise SousCategoriesInfoNotFoundError

    souscategories_info.nom = info_update.nom
    souscategories_info.id_categorie = info_update.id_categorie
    souscategories_info.created_at=info_update.created_at
    souscategories_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(souscategories_info)

    return souscategories_info


# Function to delete an souscategories info from the db
def delete_souscategories_info(session: Session, _id: int):
    souscategories_info = get_souscategories_info_by_id(session, _id)

    if souscategories_info is None:
        raise SousCategoriesInfoNotFoundError

    session.delete(souscategories_info)
    session.commit()

    return




#### categories ####
# Function to get list of categories info
def get_all_categories(session: Session, limit: int, offset: int) -> List[Categories]:
    return session.query(Categories).offset(offset).limit(limit).all()

# Function to  get info of a particular categories
def get_categories_info_by_id(session: Session, _id: int) -> Categories:
    categories_info = session.query(Categories).get(_id)

    if categories_info is None:
        raise CategoriesInfoNotFoundError

    return categories_info


# Function to add a new categories info to the database
def create_categories(session: Session, ets_info: CreateAndUpdateCategories) -> Categories:
    categories_details = session.query(Categories).filter(
            Categories.nom==ets_info.nom,
            Categories.logo_url==ets_info.logo_url,
            Categories.created_at==ets_info.created_at,
            Categories.updated_at==ets_info.updated_at
        ).first()
    if categories_details is not None:
        raise CategoriesInfoInfoAlreadyExistError

    new_categories_info = Categories(**ets_info.dict())
    session.add(new_categories_info)
    session.commit()
    session.refresh(new_categories_info)
    return new_categories_info


# Function to update details of the categories
def update_categories_info(session: Session, _id: int, info_update: CreateAndUpdateCategories) -> Categories:
    categories_info = get_categories_info_by_id(session, _id)

    if categories_info is None:
        raise CategoriesInfoNotFoundError

    categories_info.nom = info_update.nom
    categories_info.logo_url = info_update.logo_url
    categories_info.created_at=info_update.created_at
    categories_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(categories_info)

    return categories_info


# Function to delete an categories info from the db
def delete_categories_info(session: Session, _id: int):
    categories_info = get_categories_info_by_id(session, _id)

    if categories_info is None:
        raise CategoriesInfoNotFoundError

    session.delete(categories_info)
    session.commit()

    return