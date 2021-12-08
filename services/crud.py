# crud.py
from pydantic.errors import DataclassTypeError
from exceptions import (  
    BatimentsInfoInfoAlreadyExistError, BatimentsInfoNotFoundError, CategoriesInfoInfoAlreadyExistError, CategoriesInfoNotFoundError,
    CommercialsInfoInfoAlreadyExistError, CommercialsInfoNotFoundError,
    EtablissementInfoInfoAlreadyExistError, EtablissementInfoNotFoundError, FailedJobsInfoInfoAlreadyExistError, FailedJobsInfoNotFoundError, HorairesInfoInfoAlreadyExistError, HorairesInfoNotFoundError, ImagesInfoInfoAlreadyExistError, ImagesInfoNotFoundError,
    ManagersInfoInfoAlreadyExistError, ManagersInfoNotFoundError, SousCategoriesEtablissementsInfoInfoAlreadyExistError, SousCategoriesEtablissementsInfoNotFoundError,
    SousCategoriesInfoInfoAlreadyExistError, SousCategoriesInfoNotFoundError, TelephonesInfoInfoAlreadyExistError, TelephonesInfoNotFoundError, TrackingsInfoInfoAlreadyExistError, TrackingsInfoNotFoundError, UsersInfoInfoAlreadyExistError, UsersInfoNotFoundError, 
    ZonesInfoInfoAlreadyExistError, ZonesInfoNotFoundError)
#     return 
from os import SCHED_IDLE
from typing import List

from sqlalchemy.orm import Session

from models import (Batiments, Categories, Commercials,  Users,
                    Etablissements, FailedJobs, Horaires, Images, Managers, SousCategories, Telephones)
from schemas import (CreateAndUpdateBatiments, CreateAndUpdateCategories, CreateAndUpdateCommercials, 
                      CreateAndUpdateEtablissements, CreateAndUpdateFailedJobs, CreateAndUpdateHoraires, CreateAndUpdateImages,
                     CreateAndUpdateManagers, CreateAndUpdateSousCategories, CreateAndUpdateSousCategoriesEtablissements, CreateAndUpdateTelephones, CreateAndUpdateTrackings, CreateAndUpdateUsers, CreateAndUpdateZones, SousCategoriesEtablissements, Trackings, Zones)

from datetime import datetime

from fastapi import UploadFile, File
from os import getcwd, remove
from fastapi.responses import FileResponse, JSONResponse


import os
POSITION_FEE = float(os.getenv('POSITION_FEE'))
AVG_SALARY = float(os.getenv('AVG_SALARY'))
AVG_POINTS = float(os.getenv('AVG_POINTS'))
SALARY_PERCENT = float(os.getenv('SALARY_PERCENT'))


#### Position Admin ####
def chiffre_affaire(session: Session) -> float:
    return len(session.query(Etablissements).all()) * POSITION_FEE #in XAf

#get all etablissement by day
def position_get_by_day(session: Session, day: datetime):
    etablissemnts = session.query(Etablissements).all()
    journey_result = [ets for ets in etablissemnts if (ets.created_at.day == day.day and ets.created_at.month == day.month and ets.created_at.year == day.year)]
    countAndEts = {"count": len(journey_result), "cash": POSITION_FEE *len(journey_result),"etablissements": journey_result}
    if etablissemnts is None:
        raise EtablissementInfoNotFoundError
    return countAndEts

#get all etablissement by week
def position_get_by_week(session: Session, day: datetime):
    etablissements = session.query(Etablissements).all()
    week_result = [ets for ets in etablissements if ets.created_at.isocalendar()[1] == day.isocalendar()[1]]
    countAndEts = {"count": len(week_result),"cash": POSITION_FEE*len(week_result), "etablissements": week_result}
    if etablissements is None:
        raise EtablissementInfoNotFoundError
    return countAndEts

#get all etablissement by month
def position_get_by_month(session: Session, day: datetime):
    etablissements = session.query(Etablissements).all()
    month_result = [ets for ets in etablissements if (ets.created_at.month == day.month) and (ets.created_at.year == day.year)]
    countAndEts = {"count": len(month_result), "cash": POSITION_FEE *len(month_result), "etablissements": month_result}
    if etablissements is None:
        raise EtablissementInfoNotFoundError
    return countAndEts

#get all etablissement by year
def position_get_by_year(session: Session, year: int):
    etablissements = session.query(Etablissements).all()
    year_result = [ets for ets in etablissements if ets.created_at.year == year]
    countAndEts = {"count": len(year_result), "cash": POSITION_FEE *len(year_result), "etablissements": year_result}
    if etablissements is None:
        raise EtablissementInfoNotFoundError
    return countAndEts


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
    print(">>>>>>>>>>>", ets_info)
    ets_details = session.query(Etablissements).filter(
            Etablissements.idBatiment==ets_info.idBatiment,
            Etablissements.nom==ets_info.nom,
            Etablissements.indicationAdresse==ets_info.indicationAdresse,
            Etablissements.codePostal==ets_info.codePostal,
            Etablissements.siteInternet==ets_info.siteInternet,
            Etablissements.idSousCategorie==ets_info.idSousCategorie,
            Etablissements.idCommercial==ets_info.idCommercial,
            Etablissements.idManager==ets_info.idManager,
            Etablissements.etage==ets_info.etage,
            Etablissements.autres==ets_info.autres,
            Etablissements.cover==ets_info.cover,
            Etablissements.vues==ets_info.vues,
            Etablissements.created_at==ets_info.created_at,
            Etablissements.updated_at==ets_info.updated_at
        ).first()
    if ets_details is not None:
        raise EtablissementInfoInfoAlreadyExistError

    ets_info.created_at=datetime.now()
    ets_info.updated_at=datetime.now()
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

    ets_info.idBatiment = info_update.idBatiment
    ets_info.nom = info_update.nom
    ets_info.indicationAdresse = info_update.indicationAdresse
    ets_info.codePostal = info_update.codePostal
    ets_info.siteInternet = info_update.siteInternet
    ets_info.idSousCategorie = info_update.idSousCategorie
    ets_info.idCommercial = info_update.idCommercial
    ets_info.idManager=info_update.idManager
    ets_info.etage=info_update.etage
    ets_info.autres=info_update.autres
    ets_info.cover=info_update.cover
    ets_info.vues=info_update.vues
    ets_info.updated_at=datetime.now()

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
            SousCategories.logoUrl == ets_info.logoUrl,
            SousCategories.created_at==ets_info.created_at,
            SousCategories.updated_at==ets_info.updated_at
        ).first()
    if souscategories_details is not None:
        raise SousCategoriesInfoInfoAlreadyExistError

    ets_info.created_at=datetime.now()
    ets_info.updated_at=datetime.now()
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
    souscategories_info.logoUrl = info_update.logoUrl
    souscategories_info.updated_at=datetime.now()

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
# Function to get list of categories infoets_info
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
            Categories.nom==ets_info.Nom,
            Categories.logoUrl==ets_info.logoUrl,
            Categories.created_at==ets_info.created_at,
            Categories.updated_at==ets_info.updated_at
        ).first()
    if categories_details is not None:
        raise CategoriesInfoInfoAlreadyExistError
    
    ets_info.created_at=datetime.now()
    ets_info.updated_at=datetime.now()
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
    categories_info.logoUrl = info_update.logoUrl
    categories_info.updated_at=datetime.now()

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
            Managers.idUser==info.idUser,
            Managers.created_at==info.created_at,
            Managers.updated_at==info.updated_at
        ).first()
    if managers_details is not None:
        raise ManagersInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
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

    managers_info.IdUser = info_update.idUser
    managers_info.updated_at=datetime.now()

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

# Function to  get count of number of etablishments
def count_number_of_ets(session: Session, _id: int):
    commercials_info = session.query(Commercials).get(_id).etablissement
    if commercials_info is None:
        raise CommercialsInfoNotFoundError
    return len(commercials_info)

# Function to  get count and etablissemnt of a journey of a commercial
def get_ets_by_day(session: Session, _id: int, day: datetime):
    commercials_info = session.query(Commercials).get(_id).etablissement
    journey_result = [ets for ets in commercials_info if (ets.created_at.day == day.day and ets.created_at.month == day.month and ets.created_at.year == day.year)]
    countAndEts = {"count": len(journey_result), "cash": POSITION_FEE *len(journey_result),"etablissements": journey_result}
    if commercials_info is None:
        raise CommercialsInfoNotFoundError
    return countAndEts

# Function to  get count and etablissemnt of a particular week of a commercial
def get_ets_by_week(session: Session, _id: int, day: datetime):
    commercials_info = session.query(Commercials).get(_id).etablissement
    week_result = [ets for ets in commercials_info if ets.created_at.isocalendar()[1] == day.isocalendar()[1]]
    countAndEts = {"count": len(week_result),"cash": POSITION_FEE *len(week_result), "etablissements": week_result}
    if commercials_info is None:
        raise CommercialsInfoNotFoundError
    return countAndEts

# Function to  get count and etablissemnt of a month of a commercial
def get_ets_by_month(session: Session, _id: int, day: datetime):
    commercials_info = session.query(Commercials).get(_id).etablissement
    month_result = [ets for ets in commercials_info if (ets.created_at.month == day.month) and (ets.created_at.year == day.year)]
    countAndEts = {"count": len(month_result), "cash": POSITION_FEE *len(month_result), "etablissements": month_result}
    if commercials_info is None:
        raise CommercialsInfoNotFoundError
    return countAndEts

# Function to  get count and etablissemnt of a year of a commercial
def get_ets_by_year(session: Session, _id: int, year: int):
    commercials_info = session.query(Commercials).get(_id).etablissement
    year_result = [ets for ets in commercials_info if ets.created_at.year == year]
    countAndEts = {"count": len(year_result), "cash": POSITION_FEE *len(year_result), "etablissements": year_result}
    if commercials_info is None:
        raise CommercialsInfoNotFoundError
    return countAndEts

# Function to  get salary of a commercial
def get_comm_salary(session: Session, _id: int, day: datetime):
    commercials_info = session.query(Commercials).get(_id).etablissement
    month_result = [ets for ets in commercials_info if (ets.created_at.month == day.month) and (ets.created_at.year == day.year)]
    return len(month_result) * SALARY_PERCENT

# Function to get list of commercials info by town
def get_all_commercials_by_ville(session: Session, limit: int, offset: int, ville: str) -> List[Commercials]:
    return session.query(Commercials).filter(Commercials.ville==ville).offset(offset).limit(limit).all()

# Function to get list of commercials info by quartier
def get_all_commercials_by_quartier(session: Session, limit: int, offset: int, quartier: str) -> List[Commercials]:
    return session.query(Commercials).filter(Commercials.quartier==quartier).offset(offset).limit(limit).all()

# Function to add a new commercials info to the database
def create_commercials(session: Session, info: CreateAndUpdateCommercials) -> Commercials:
    commercials_details = session.query(Commercials).filter(
            Commercials.idUser==info.idUser,
            Commercials.numeroCni==info.numeroCni,
            Commercials.ville==info.ville,
            Commercials.quartier==info.quartier,
            Commercials.imageProfil==info.imageProfil,
            Commercials.idZone==info.idZone,
            Commercials.actif==info.actif,
            Commercials.sexe==info.sexe,
            Commercials.whatsapp==info.whatsapp,
            Commercials.diplome==info.diplome,
            Commercials.tailleTshirt==info.tailleTshirt,
            Commercials.age==info.age,
            Commercials.created_at==info.created_at,
            Commercials.updated_at==info.updated_at
        ).first()
    if commercials_details is not None:
        raise CommercialsInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
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

    commercials_info.idUser = info_update.idUser
    commercials_info.numeroCni=info_update.numeroCni
    commercials_info.numeroBadge=info_update.numeroBadge
    commercials_info.ville=info_update.ville
    commercials_info.quartier=info_update.quartier
    commercials_info.imageProfil=info_update.imageProfil
    commercials_info.idZone=info_update.idZone
    commercials_info.actif=info_update.actif
    commercials_info.sexe=info_update.sexe
    commercials_info.whatsapp=info_update.whatsapp
    commercials_info.diplome=info_update.diplome
    commercials_info.tailleTshirt=info_update.tailleTshirt
    commercials_info.age=info_update.age
    commercials_info.updated_at=datetime.now()

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
            Horaires.idEtablissement==info.idEtablissement,
            Horaires.jour==info.jour,
            Horaires.ouvert==info.ouvert,
            Horaires.heureOuverture==info.heureOuverture,
            Horaires.heureFermeture==info.heureFermeture,
            Horaires.created_at==info.created_at,
            Horaires.updated_at==info.updated_at
        ).first()
    if horaires_details is not None:
        raise HorairesInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
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

    horaires_info.idEtablissement = info_update.idEtablissement
    horaires_info.jour=info_update.jour
    horaires_info.ouvert=info_update.ouvert
    horaires_info.heureOuverture=info_update.heureOuverture
    horaires_info.heureFermeture=info_update.heureFermeture
    horaires_info.updated_at=datetime.now()

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
            Images.idEtablissement==info.idEtablissement,
            Images.imageUrl==info.imageUrl,
            Images.created_at==info.created_at,
            Images.updated_at==info.updated_at
        ).first()
    if images_details is not None:
        raise ImagesInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
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

    images_info.idEtablissement = info_update.idEtablissement
    images_info.imageUrl=info_update.imageUrl
    images_info.updated_at=datetime.now()

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
            Telephones.idEtablissement==info.idEtablissement,
            Telephones.numero==info.numero,
            Telephones.whatsapp==info.whatsapp,
            Telephones.created_at==info.created_at,
            Telephones.updated_at==info.updated_at
        ).first()
    if telephones_details is not None:
        raise TelephonesInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
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

    telephones_info.idEtablissement = info_update.idEtablissement
    telephones_info.numero=info_update.numero
    telephones_info.whatsapp=info_update.whatsapp
    telephones_info.updated_at=datetime.now()

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


#### Batiments ####
# Function to get list of Batiments info
def get_all_batiments(session: Session, limit: int, offset: int) -> List[Batiments]:
    return session.query(Batiments).offset(offset).limit(limit).all()

# Function to  get info of a particular Batiments
def get_batiments_info_by_id(session: Session, _id: int) -> Batiments:
    batiments_info = session.query(Batiments).get(_id)

    if batiments_info is None:
        raise BatimentsInfoNotFoundError

    return batiments_info

# Function to add a new Batiments info to the database
def create_Batiments(session: Session, info: CreateAndUpdateBatiments) -> Batiments:
    batiments_details = session.query(Batiments).filter(
            Batiments.nom == info.nom,
            Batiments.nombreNiveaux == info.nombreNiveaux,
            Batiments.codeBatiment == info.codeBatiment,
            Batiments.longitude == info.longitude,
            Batiments.latitude == info.latitude,
            Batiments.image == info.image,
            Batiments.indication == info.indication,
            Batiments.rue == info.rue,
            Batiments.ville == info.ville,
            Batiments.commune == info.commune,
            Batiments.quartier == info.quartier,
            Batiments.created_at == info.created_at,
            Batiments.updated_at == info.updated_at,
        ).first()
    if batiments_details is not None:
        raise BatimentsInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_batiments_info = Batiments(**info.dict())
    session.add(new_batiments_info)
    session.commit()
    session.refresh(new_batiments_info)
    return new_batiments_info

# Function to update details of the Batiments
def update_batiments_info(session: Session, _id: int, info_update: CreateAndUpdateBatiments) -> Batiments:
    batiments_info = get_batiments_info_by_id(session, _id)

    if batiments_info is None:
        raise BatimentsInfoNotFoundError

    batiments_info.nom = info_update.nom
    batiments_info.nombreNiveaux=info_update.nombreNiveaux
    batiments_info.codeBatiment=info_update.codeBatiment
    batiments_info.longitude=info_update.longitude
    batiments_info.latitude=info_update.latitude
    batiments_info.image=info_update.image
    batiments_info.codeBatiment=info_update.codeBatiment
    batiments_info.indication=info_update.indication
    batiments_info.rue=info_update.rue
    batiments_info.ville=info_update.ville
    batiments_info.commune=info_update.commune
    batiments_info.quartier=info_update.quartier
    batiments_info.updated_at=datetime.now()

    session.commit()
    session.refresh(batiments_info)

    return batiments_info

# Function to delete an Batiments info from the db
def delete_batiments_info(session: Session, _id: int):
    batiments_info = get_batiments_info_by_id(session, _id)

    if batiments_info is None:
        raise BatimentsInfoNotFoundError

    session.delete(batiments_info)
    session.commit()


#### FailedJobs ####
# Function to get list of FailedJobs info
def get_all_failedJobs(session: Session, limit: int, offset: int) -> List[FailedJobs]:
    return session.query(FailedJobs).offset(offset).limit(limit).all()

# Function to  get info of a particular FailedJobs
def get_failedJobs_info_by_id(session: Session, _id: int) -> FailedJobs:
    failedJobs_info = session.query(FailedJobs).get(_id)

    if failedJobs_info is None:
        raise FailedJobsInfoNotFoundError

    return failedJobs_info

# Function to add a new FailedJobs info to the database
def create_FailedJobs(session: Session, info: CreateAndUpdateFailedJobs) -> FailedJobs:
    failedJobs_details = session.query(FailedJobs).filter(
            FailedJobs.uuid == info.uuid,
            FailedJobs.connection == info.connection,
            FailedJobs.queue == info.queue,
            FailedJobs.payload == info.payload,
            FailedJobs.exception == info.exception,
            FailedJobs.failed_at == info.failed_at,
        ).first()
    if failedJobs_details is not None:
        raise FailedJobsInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_failedJobs_info = FailedJobs(**info.dict())
    session.add(new_failedJobs_info)
    session.commit()
    session.refresh(new_failedJobs_info)
    return new_failedJobs_info

# Function to update details of the FailedJobs
def update_failedJobs_info(session: Session, _id: int, info_update: CreateAndUpdateFailedJobs) -> FailedJobs:
    failedJobs_info = get_failedJobs_info_by_id(session, _id)

    if failedJobs_info is None:
        raise FailedJobsInfoNotFoundError

    failedJobs_info.uuid = info_update.uuid
    failedJobs_info.connection=info_update.connection
    failedJobs_info.queue=info_update.queue
    failedJobs_info.payload=info_update.payload
    failedJobs_info.exception=info_update.exception
    failedJobs_info.failed_at=info_update.failed_at

    session.commit()
    session.refresh(failedJobs_info)

    return failedJobs_info

# Function to delete an FailedJobs info from the db
def delete_failedJobs_info(session: Session, _id: int):
    failedJobs_info = get_failedJobs_info_by_id(session, _id)

    if failedJobs_info is None:
        raise FailedJobsInfoNotFoundError

    session.delete(failedJobs_info)
    session.commit()




#### Users ####
# Function to get list of Users info
def get_all_users(session: Session, limit: int, offset: int) -> List[Users]:
    return session.query(Users).offset(offset).limit(limit).all()

# Function to  get info of a particular FailedJobs
def get_users_info_by_id(session: Session, _id: int) -> Users:
    users_info = session.query(Users).get(_id)

    if users_info is None:
        raise UsersInfoNotFoundError

    return users_info

# Function to add a new Users info to the database
def create_Users(session: Session, info: CreateAndUpdateUsers) -> Users:
    users_details = session.query(Users).filter(
            Users.name == info.name,
            Users.email == info.email,
            Users.email_verified_at == info.email_verified_at,
            Users.password == info.password,
            Users.phone == info.phone,
            Users.role == info.role,
            Users.remember_token == info.remember_token,
            Users.created_at == info.created_at,
            Users.role == info.role,
            Users.created_at == info.created_at,
            Users.updated_at == info.updated_at,
        ).first()
    if users_details is not None:
        raise UsersInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_users_info = Users(**info.dict())
    session.add(new_users_info)
    session.commit()
    session.refresh(new_users_info)
    return new_users_info

# Function to update details of the Users
def update_users_info(session: Session, _id: int, info_update: CreateAndUpdateUsers) -> Users:
    users_info = get_users_info_by_id(session, _id)

    if users_info is None:
        raise UsersInfoNotFoundError

    users_info.name = info_update.name
    users_info.email=info_update.email
    users_info.email_verified_at=info_update.email_verified_at
    users_info.password=info_update.password
    users_info.phone=info_update.phone
    users_info.role=info_update.role
    users_info.remember_token=info_update.remember_token

    session.commit()
    session.refresh(users_info)

    return users_info

# Function to delete an Users info from the db
def delete_users_info(session: Session, _id: int):
    users_info = get_users_info_by_id(session, _id)

    if users_info is None:
        raise UsersInfoNotFoundError

    session.delete(users_info)
    session.commit()



#### Trackings ####
# Function to get list of Trackings info
def get_all_trackings(session: Session, limit: int, offset: int) -> List[Trackings]:
    return session.query(Trackings).offset(offset).limit(limit).all()

# Function to  get info of a particular FailedJobs
def get_trackings_info_by_id(session: Session, _id: int) -> Trackings:
    trackings_info = session.query(Trackings).get(_id)

    if trackings_info is None:
        raise TrackingsInfoNotFoundError

    return trackings_info

# Function to add a new Trackings info to the database
def create_Trackings(session: Session, info: CreateAndUpdateTrackings) -> Trackings:
    trackings_details = session.query(Trackings).filter(
            Trackings.idUser == info.idUser,
            Trackings.longitude == info.longitude,
            Trackings.latitude == info.latitude,
            Trackings.created_at == info.created_at,
            Trackings.updated_at == info.updated_at,
        ).first()
    if trackings_details is not None:
        raise TrackingsInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_trackings_info = Trackings(**info.dict())
    session.add(new_trackings_info)
    session.commit()
    session.refresh(new_trackings_info)
    return new_trackings_info

# Function to update details of the Trackings
def update_trackings_info(session: Session, _id: int, info_update: CreateAndUpdateTrackings) -> Trackings:
    trackings_info = get_trackings_info_by_id(session, _id)

    if trackings_info is None:
        raise TrackingsInfoNotFoundError

    trackings_info.idUser = info_update.idUser
    trackings_info.longitude=info_update.longitude
    trackings_info.latitude=info_update.latitude

    session.commit()
    session.refresh(trackings_info)

    return trackings_info

# Function to delete an Trackings info from the db
def delete_trackings_info(session: Session, _id: int):
    trackings_info = get_trackings_info_by_id(session, _id)

    if trackings_info is None:
        raise TrackingsInfoNotFoundError

    session.delete(trackings_info)
    session.commit()


#### Zones ####
# Function to get list of Zones info
def get_all_zones(session: Session, limit: int, offset: int) -> List[Zones]:
    return session.query(Zones).offset(offset).limit(limit).all()

# Function to  get info of a particular FailedJobs
def get_zones_info_by_id(session: Session, _id: int) -> Zones:
    zones_info = session.query(Zones).get(_id)

    if zones_info is None:
        raise ZonesInfoNotFoundError

    return zones_info

# Function to add a new Zones info to the database
def create_Zones(session: Session, info: CreateAndUpdateZones) -> Zones:
    zones_details = session.query(Zones).filter(
            Zones.nom == info.nom,
            Zones.ville == info.ville,
            Zones.created_at == info.created_at,
            Zones.updated_at == info.updated_at,
        ).first()
    if zones_details is not None:
        raise ZonesInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_zones_info = Zones(**info.dict())
    session.add(new_zones_info)
    session.commit()
    session.refresh(new_zones_info)
    return new_zones_info

# Function to update details of the Zones
def update_zones_info(session: Session, _id: int, info_update: CreateAndUpdateZones) -> Zones:
    zones_info = get_zones_info_by_id(session, _id)

    if zones_info is None:
        raise ZonesInfoNotFoundError

    zones_info.nom = info_update.nom
    zones_info.ville=info_update.ville

    session.commit()
    session.refresh(zones_info)

    return zones_info

# Function to delete an Zones info from the db
def delete_zones_info(session: Session, _id: int):
    zones_info = get_zones_info_by_id(session, _id)

    if zones_info is None:
        raise ZonesInfoNotFoundError

    session.delete(zones_info)
    session.commit()



#### SousCategoriesEtablissements ####
# Function to get list of SousCategoriesEtablissements info
def get_all_sousCategoriesEtablissements(session: Session, limit: int, offset: int) -> List[SousCategoriesEtablissements]:
    return session.query(SousCategoriesEtablissements).offset(offset).limit(limit).all()

# Function to  get info of a particular FailedJobs
def get_sousCategoriesEtablissements_info_by_id(session: Session, _id: int) -> SousCategoriesEtablissements:
    sousCategoriesEtablissements_info = session.query(SousCategoriesEtablissements).get(_id)

    if sousCategoriesEtablissements_info is None:
        raise SousCategoriesEtablissementsInfoNotFoundError

    return sousCategoriesEtablissements_info

# Function to add a new SousCategoriesEtablissements info to the database
def create_SousCategoriesEtablissements(session: Session, info: CreateAndUpdateSousCategoriesEtablissements) -> SousCategoriesEtablissements:
    sousCategoriesEtablissements_details = session.query(SousCategoriesEtablissements).filter(
            SousCategoriesEtablissements.idEtablissement == info.idEtablissement,
            SousCategoriesEtablissements.idSousCategorie == info.idSousCategorie,
            SousCategoriesEtablissements.created_at == info.created_at,
            SousCategoriesEtablissements.updated_at == info.updated_at,
        ).first()
    if sousCategoriesEtablissements_details is not None:
        raise SousCategoriesEtablissementsInfoInfoAlreadyExistError

    info.created_at=datetime.now()
    info.updated_at=datetime.now()
    new_sousCategoriesEtablissements_info = SousCategoriesEtablissements(**info.dict())
    session.add(new_sousCategoriesEtablissements_info)
    session.commit()
    session.refresh(new_sousCategoriesEtablissements_info)
    return new_sousCategoriesEtablissements_info

# Function to update details of the SousCategoriesEtablissements
def update_sousCategoriesEtablissements_info(session: Session, _id: int, info_update: CreateAndUpdateSousCategoriesEtablissements) -> SousCategoriesEtablissements:
    sousCategoriesEtablissements_info = get_sousCategoriesEtablissements_info_by_id(session, _id)

    if sousCategoriesEtablissements_info is None:
        raise SousCategoriesEtablissementsInfoNotFoundError

    sousCategoriesEtablissements_info.idEtablissement = info_update.idEtablissement
    sousCategoriesEtablissements_info.idSousCategorie=info_update.idSousCategorie

    session.commit()
    session.refresh(sousCategoriesEtablissements_info)

    return sousCategoriesEtablissements_info

# Function to delete an SousCategoriesEtablissements info from the db
def delete_sousCategoriesEtablissements_info(session: Session, _id: int):
    sousCategoriesEtablissements_info = get_sousCategoriesEtablissements_info_by_id(session, _id)

    if sousCategoriesEtablissements_info is None:
        raise SousCategoriesEtablissementsInfoNotFoundError

    session.delete(sousCategoriesEtablissements_info)
    session.commit()


