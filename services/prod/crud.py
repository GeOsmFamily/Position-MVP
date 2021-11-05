# crud.py
from exceptions import (  
    CategoriesInfoInfoAlreadyExistError, CategoriesInfoNotFoundError,
    CommercialsInfoInfoAlreadyExistError, CommercialsInfoNotFoundError,
    EtablissementInfoInfoAlreadyExistError, EtablissementInfoNotFoundError, HorairesInfoInfoAlreadyExistError, HorairesInfoNotFoundError, ImagesInfoInfoAlreadyExistError, ImagesInfoNotFoundError,
    ManagersInfoInfoAlreadyExistError, ManagersInfoNotFoundError,
    SousCategoriesInfoInfoAlreadyExistError, SousCategoriesInfoNotFoundError, TelephonesInfoInfoAlreadyExistError, TelephonesInfoNotFoundError)
#     return 
from os import SCHED_IDLE
from typing import List

from sqlalchemy.orm import Session

from models import (Categories, Commercials,  
                    Etablissements, Horaires, Images, Managers, SousCategories, Telephones)
from schemas import (CreateAndUpdateCategories, 
                     CreateAndUpdateCommercials, CreateAndUpdateEtablissements, CreateAndUpdateHoraires, CreateAndUpdateImages,
                     CreateAndUpdateManagers, CreateAndUpdateSousCategories, CreateAndUpdateTelephones)


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



#### Managers ####
# Function to get list of managers info
def get_all_managers(session: Session, limit: int, offset: int) -> List[Managers]:
    return session.query(Managers).offset(offset).limit(limit).all()

# Function to  get info of a particular categories
def get_managers_info_by_id(session: Session, _id: int) -> Managers:
    managers_info = session.query(Managers).get(_id)

    if managers_info is None:
        raise ManagersInfoNotFoundError

    return managers_info

# Function to add a new managers info to the database
def create_managers(session: Session, info: CreateAndUpdateManagers) -> Managers:
    managers_details = session.query(Managers).filter(
            Managers.id_user==info.id_user,
            Managers.created_at==info.created_at,
            Managers.updated_at==info.updated_at
        ).first()
    if managers_details is not None:
        raise ManagersInfoInfoAlreadyExistError

    new_categories_info = Managers(**info.dict())
    session.add(new_categories_info)
    session.commit()
    session.refresh(new_categories_info)
    return new_categories_info

# Function to update details of the managers
def update_managers_info(session: Session, _id: int, info_update: CreateAndUpdateManagers) -> Managers:
    managers_info = get_managers_info_by_id(session, _id)

    if managers_info is None:
        raise ManagersInfoNotFoundError

    managers_info.nom = info_update.id_user
    managers_info.created_at=info_update.created_at
    managers_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(managers_info)

    return managers_info

# Function to delete an managers info from the db
def delete_managers_info(session: Session, _id: int):
    managers_info = get_managers_info_by_id(session, _id)

    if managers_info is None:
        raise ManagersInfoNotFoundError

    session.delete(managers_info)
    session.commit()

    return


#### Commercials ####
# Function to get list of commercials info
def get_all_commercials(session: Session, limit: int, offset: int) -> List[Commercials]:
    return session.query(Commercials).offset(offset).limit(limit).all()

# Function to  get info of a particular commercials
def get_commercials_info_by_id(session: Session, _id: int) -> Commercials:
    commercials_info = session.query(Commercials).get(_id)
    if commercials_info is None:
        raise CommercialsInfoNotFoundError

    return commercials_info

# Function to get list of commercials info by town
def get_all_commercials_by_town(session: Session, limit: int, offset: int, town: str) -> List[Commercials]:
    return session.query(Commercials).filter(Commercials.ville==town).offset(offset).limit(limit).all()

# Function to get list of commercials info by quartier
def get_all_commercials_by_quartier(session: Session, limit: int, offset: int, quartier: str) -> List[Commercials]:
    return session.query(Commercials).filter(Commercials.quartier==quartier).offset(offset).limit(limit).all()

# Function to add a new commercials info to the database
def create_commercials(session: Session, info: CreateAndUpdateCommercials) -> Commercials:
    commercials_details = session.query(Commercials).filter(
            Commercials.revenu_total==info.revenu_total,
            Commercials.id_user==info.id_user,
            Commercials.nombre_etablissement==info.nombre_etablissement,
            Commercials.numero_cni==info.numero_cni,
            Commercials.ville==info.ville,
            Commercials.quartier==info.quartier,
            Commercials.image_profil==info.image_profil,
            Commercials.created_at==info.created_at,
            Commercials.updated_at==info.updated_at
            
        ).first()
    if commercials_details is not None:
        raise CommercialsInfoInfoAlreadyExistError

    new_commercials_info = Commercials(**info.dict())
    session.add(new_commercials_info)
    session.commit()
    session.refresh(new_commercials_info)
    return new_commercials_info

# Function to update details of the commercials
def update_commercials_info(session: Session, _id: int, info_update: CreateAndUpdateCommercials) -> Commercials:
    commercials_info = get_commercials_info_by_id(session, _id)

    if commercials_info is None:
        raise CommercialsInfoNotFoundError

    commercials_info.revenu_total = info_update.revenu_total
    commercials_info.id_user=info_update.id_user
    commercials_info.nombre_etablissement=info_update.nombre_etablissement
    commercials_info.numero_cni=info_update.numero_cni
    commercials_info.ville=info_update.ville
    commercials_info.quartier=info_update.quartier
    commercials_info.image_profil=info_update.image_profil
    commercials_info.updated_at=info_update.created_at
    commercials_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(commercials_info)

    return commercials_info

# Function to delete an commercials info from the db
def delete_commercials_info(session: Session, _id: int):
    commercials_info = get_commercials_info_by_id(session, _id)

    if commercials_info is None:
        raise CommercialsInfoNotFoundError

    session.delete(commercials_info)
    session.commit()

    return 


#### Horaires ####
# Function to get list of Horaires info
def get_all_horaires(session: Session, limit: int, offset: int) -> List[Horaires]:
    return session.query(Horaires).offset(offset).limit(limit).all()

# Function to  get info of a particular Horaires
def get_horaires_info_by_id(session: Session, _id: int) -> Horaires:
    horaires_info = session.query(Horaires).get(_id)

    if horaires_info is None:
        raise HorairesInfoNotFoundError

    return horaires_info

# Function to add a new Horaires info to the database
def create_horaires(session: Session, info: CreateAndUpdateHoraires) -> Horaires:
    horaires_details = session.query(Horaires).filter(
            Horaires.id_etablissement==info.id_etablissement,
            Horaires.jour==info.jour,
            Horaires.ouvert==info.ouvert,
            Horaires.heureOuverture==info.heureOuverture,
            Horaires.heureFermeture==info.heureFermeture,
            Horaires.created_at==info.created_at,
            Horaires.updated_at==info.updated_at
        ).first()
    if horaires_details is not None:
        raise HorairesInfoInfoAlreadyExistError

    new_horaires_info = Horaires(**info.dict())
    session.add(new_horaires_info)
    session.commit()
    session.refresh(new_horaires_info)
    return new_horaires_info

# Function to update details of the Horaires
def update_horaires_info(session: Session, _id: int, info_update: CreateAndUpdateHoraires) -> Horaires:
    horaires_info = get_horaires_info_by_id(session, _id)

    if horaires_info is None:
        raise HorairesInfoNotFoundError

    horaires_info.id_etablissement = info_update.id_etablissement
    horaires_info.jour=info_update.jour
    horaires_info.ouvert=info_update.ouvert
    horaires_info.heureOuverture=info_update.heureOuverture
    horaires_info.heureFermeture=info_update.heureFermeture
    horaires_info.updated_at=info_update.created_at
    horaires_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(horaires_info)

    return horaires_info

# Function to delete an Horaires info from the db
def delete_horaires_info(session: Session, _id: int):
    horaires_info = get_horaires_info_by_id(session, _id)

    if horaires_info is None:
        raise HorairesInfoNotFoundError

    session.delete(horaires_info)
    session.commit()


#### Images ####
# Function to get list of Images info
def get_all_images(session: Session, limit: int, offset: int) -> List[Images]:
    return session.query(Images).offset(offset).limit(limit).all()

# Function to  get info of a particular Images
def get_images_info_by_id(session: Session, _id: int) -> Images:
    images_info = session.query(Images).get(_id)

    if images_info is None:
        raise ImagesInfoNotFoundError

    return images_info

# Function to add a new Images info to the database
def create_Images(session: Session, info: CreateAndUpdateImages) -> Images:
    images_details = session.query(Images).filter(
            Images.id_etablissement==info.id_etablissement,
            Images.images_url==info.image_url,
            Images.created_at==info.created_at,
            Images.updated_at==info.updated_at
        ).first()
    if images_details is not None:
        raise ImagesInfoInfoAlreadyExistError

    new_images_info = Images(**info.dict())
    session.add(new_images_info)
    session.commit()
    session.refresh(new_images_info)
    return new_images_info

# Function to update details of the Images
def update_images_info(session: Session, _id: int, info_update: CreateAndUpdateImages) -> Images:
    images_info = get_images_info_by_id(session, _id)

    if images_info is None:
        raise ImagesInfoNotFoundError

    images_info.id_etablissement = info_update.id_etablissement
    images_info.image_url=info_update.image_url
    images_info.updated_at=info_update.created_at
    images_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(images_info)

    return images_info

# Function to delete an Images info from the db
def delete_images_info(session: Session, _id: int):
    images_info = get_images_info_by_id(session, _id)

    if images_info is None:
        raise ImagesInfoNotFoundError

    session.delete(images_info)
    session.commit()



#### Telephones ####
# Function to get list of Telephones info
def get_all_telephones(session: Session, limit: int, offset: int) -> List[Telephones]:
    return session.query(Telephones).offset(offset).limit(limit).all()

# Function to  get info of a particular Telephones
def get_telephones_info_by_id(session: Session, _id: int) -> Telephones:
    telephones_info = session.query(Telephones).get(_id)

    if telephones_info is None:
        raise TelephonesInfoNotFoundError

    return telephones_info

# Function to add a new Telephones info to the database
def create_Telephones(session: Session, info: CreateAndUpdateTelephones) -> Telephones:
    telephones_details = session.query(Telephones).filter(
            Telephones.id_etablissement==info.id_etablissement,
            Telephones.numero==info.numero,
            Telephones.whatsapp==info.whatsapp,
            Telephones.created_at==info.created_at,
            Telephones.updated_at==info.updated_at
        ).first()
    if telephones_details is not None:
        raise TelephonesInfoInfoAlreadyExistError

    new_telephones_info = Telephones(**info.dict())
    session.add(new_telephones_info)
    session.commit()
    session.refresh(new_telephones_info)
    return new_telephones_info

# Function to update details of the Telephones
def update_telephones_info(session: Session, _id: int, info_update: CreateAndUpdateTelephones) -> Telephones:
    telephones_info = get_telephones_info_by_id(session, _id)

    if telephones_info is None:
        raise TelephonesInfoNotFoundError

    telephones_info.id_etablissement = info_update.id_etablissement
    telephones_info.numer=info_update.numero
    telephones_info.whatsapp=info_update.whatsapp
    telephones_info.updated_at=info_update.created_at
    telephones_info.updated_at=info_update.updated_at

    session.commit()
    session.refresh(telephones_info)

    return telephones_info

# Function to delete an Telephones info from the db
def delete_telephones_info(session: Session, _id: int):
    telephones_info = get_telephones_info_by_id(session, _id)

    if telephones_info is None:
        raise TelephonesInfoNotFoundError

    session.delete(telephones_info)
    session.commit()
