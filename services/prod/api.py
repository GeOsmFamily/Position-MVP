# api.py
from datetime import datetime
import datetime as datetime2

from exceptions import (BatimentsInfoException, CategoriesInfoException, CommercialsInfoException,
                        EtablissementInfoException, FTPImagesException, FailedJobsInfoException, HorairesInfoException,
                        ImagesInfoException, ManagersInfoException, SousCategorieEtablissementsInfoException,
                        SousCategoriesInfoException, TelephonesInfoException, TrackingsInfoException, UsersInfoException, ZonesInfoException)

from fastapi import APIRouter, Depends, Header, HTTPException
from fastapi_utils.cbv import cbv
from sqlalchemy.orm import Session

from auth import has_authority, verify_token
from crud import (chiffre_affaire, count_number_of_ets, create_Batiments, create_FailedJobs, create_SousCategorieEtablissements, create_Trackings, create_Users, create_Zones, create_categories, create_commercials, create_ets,
                  create_horaires, create_Images, create_managers,
                  create_souscategories, create_Telephones, delete_batiments_info,
                  delete_categories_info, delete_commercials_info,
                  delete_ets_info, delete_failedJobs_info, delete_horaires_info, delete_images_info,
                  delete_managers_info, delete_sousCategorieEtablissements_info, delete_souscategories_info,
                  delete_telephones_info, delete_trackings_info, delete_users_info, delete_zones_info, get_all_batiments, get_all_categories,
                  get_all_commercials, get_all_commercials_by_quartier,
                  get_all_commercials_by_ville, get_all_ets,
                  get_all_ets_by_payment, get_all_failedJobs, get_all_horaires, get_all_images,
                  get_all_managers, get_all_sousCategorieEtablissements, get_all_souscategories, get_all_telephones, get_all_trackings, get_all_users, get_all_zones, get_batiments_info_by_id,
                  get_categories_info_by_id, get_comm_salary, get_commercials_info_by_id,
                  get_etablissement_info_by_id, get_ets_by_day, get_ets_by_month, get_ets_by_week, get_ets_by_year, get_failedJobs_info_by_id, get_images_info_by_id,
                  get_managers_info_by_id, get_sousCategorieEtablissements_info_by_id, get_souscategories_info_by_id,
                  get_telephones_info_by_id, get_trackings_info_by_id, get_users_info_by_id, get_zones_info_by_id, position_get_by_day, position_get_by_month, position_get_by_week, position_get_by_year, update_batiments_info, update_categories_info,
                  update_commercials_info, update_ets_info, update_failedJobs_info,
                  update_horaires_info, update_images_info,
                  update_managers_info, update_sousCategorieEtablissements_info, update_souscategories_info,
                  update_telephones_info, update_trackings_info, update_users_info, update_zones_info)
from database import get_db
from schemas import (Batiments, Categories, Commercials, CreateAndUpdateBatiments, CreateAndUpdateCategories,
                     CreateAndUpdateCommercials, CreateAndUpdateEtablissements, CreateAndUpdateFailedJobs,
                     CreateAndUpdateHoraires, CreateAndUpdateImages,
                     CreateAndUpdateManagers, CreateAndUpdateSousCategorieEtablissements, CreateAndUpdateSousCategories,
                     CreateAndUpdateTelephones, CreateAndUpdateTrackings, CreateAndUpdateUsers, CreateAndUpdateZones, Etablissements, FailedJobs, Horaires,
                     Images, Managers, PaginateSousCategorieEtablissementsInfo, PaginateZonesInfo, PaginatedBatimentsInfo, PaginatedCategoriesInfo,
                     PaginatedCommercialsInfo, PaginatedEtablissementInfo, PaginatedFailedJobsInfo,
                     PaginatedHorairesInfo, PaginatedImagesInfo,
                     PaginatedManagersInfo, PaginatedSousCategoriesInfo,
                     PaginatedTelephonesInfo, PaginatedTrackingsInfo, PaginatedUsersInfo, SousCategorieEtablissements, SousCategories, Telephones, Trackings, Users, Zones)

from fastapi import UploadFile, File
from os import getcwd, remove
from fastapi.responses import FileResponse, JSONResponse

from utils import create_qr, read_last_image_identification, save_last_image_identification

router = APIRouter()

from fastapi import Request
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates






# POSITION ADMIN
# API endpoint to get info of a particular etablissement
@router.get("/position/chiffre_affaire",)
def get_chiffre_affaire(session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        chiffreA = chiffre_affaire(session)
        return chiffreA 
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)
    
# API endpoint to get count of etablissement of a commercial
@router.get("/position/statistics/get/ets/by_day/", )
def get_position_info(day: datetime, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        position_info = position_get_by_day(session, day=day)
        return position_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/position/statistics/get/ets/by_week/", )
def get_position_info(aDayOfTheWeek: datetime, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        position_info = position_get_by_week(session, day=aDayOfTheWeek)
        return position_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/position/statistics/get/ets/by_month/", )
def get_position_info(month: int, year: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        x_date = datetime2.datetime(year, month, 1) # random date just to target the month
        position_info = position_get_by_month(session, day=x_date)
        return position_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/position/statistics/get/ets/by_year/", )
def get_position_info(year: int ,session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        position_info = position_get_by_year(session, year=year)
        return position_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Etablissement ####
@cbv(router)
class Etablissement:
    session: Session = Depends(get_db)

    # API to get the list of Etablissement info
    @router.get("/etablissements", response_model=PaginatedEtablissementInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        print(">>>>>>>>>>>", authorization)
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        etablissement_list = get_all_ets(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": etablissement_list}

        return response

    # API to get the list of Etablissement info
    @router.get("/etablissements/pay", response_model=PaginatedEtablissementInfo)
    def list_ets_by_payment(self, paid: int, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        etablissement_list = get_all_ets_by_payment(self.session, limit, offset, pay=paid)
        response = {"limit": limit, "offset": offset, "data": etablissement_list}

        return response

    # API endpoint to add a Etablissement info to the database
    @router.post("/etablissement")
    def add_ets(self, ets_info: CreateAndUpdateEtablissements, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            ets_info = create_ets(self.session, ets_info)
            return ets_info
        except EtablissementInfoException as cie:
            raise HTTPException(**cie.__dict__)
        
# API endpoint to get info of a particular etablissement
@router.get("/etablissement/", response_model=Etablissements)
def get_ets_info(etablissement_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        ets_info = get_etablissement_info_by_id(session, etablissement_id)
        return ets_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing etablissement info
@router.put("/etablissement/", response_model=Etablissements)
def update_etablissement(etablissement_id: int, new_info: CreateAndUpdateEtablissements, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        ets_info = update_ets_info(session, etablissement_id, new_info)
        return ets_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to delete a etablissement info from the data base
@router.delete("/etablissement/")
def delete_etablissement(etablissement_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_ets_info(session, etablissement_id)
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### SousCategories ####
@cbv(router)
class SousCategorie:
    session: Session = Depends(get_db)

    # API to get the list of souscategories info
    @router.get("/souscategories", response_model=PaginatedSousCategoriesInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        souscategories_list = get_all_souscategories(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": souscategories_list}

        return response

    # API endpoint to add a souscategories info to the database
    @router.post("/souscategories")
    def add_souscategories(self, souscategories_info: CreateAndUpdateSousCategories, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            souscategories_info = create_souscategories(self.session, souscategories_info)
            return souscategories_info
        except SousCategoriesInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular souscategories
@router.get("/souscategories/", response_model=SousCategories)
def get_souscategories_info(souscategories_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        souscategories_info = get_souscategories_info_by_id(session, souscategories_id)
        return souscategories_info
    except SousCategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing souscategories info
@router.put("/souscategories/", response_model=SousCategories)
def update_souscategories(souscategories_id: int, new_info: CreateAndUpdateSousCategories, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        souscategories_info = update_souscategories_info(session, souscategories_id, new_info)
        return souscategories_info
    except SousCategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to delete a souscategories info from the data base
@router.delete("/souscategories/")
def delete_souscategories(souscategories_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_souscategories_info(session, souscategories_id)
    except SousCategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Categories ####
@cbv(router)
class Categorie:
    session: Session = Depends(get_db)

    # API to get the list of categories info
    @router.get("/categories", response_model=PaginatedCategoriesInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        categories_list = get_all_categories(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": categories_list}

        return response

    # API endpoint to add a categories info to the database
    @router.post("/categories")
    def add_categories(self, categories_info: CreateAndUpdateCategories, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            categories_info = create_categories(self.session, categories_info)
            return categories_info
        except CategoriesInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular categories
@router.get("/categories/", response_model=Categories)
def get_categories_info(categories_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        categories_info = get_categories_info_by_id(session, categories_id)
        return categories_info
    except CategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing categories info
@router.put("/categories/", response_model=Categories)
def update_categories(categories_id: int, new_info: CreateAndUpdateCategories, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        categories_info = update_categories_info(session, categories_id, new_info)
        return categories_info
    except CategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to delete a categories info from the data base
@router.delete("/categories/")
def delete_categories(categories_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_categories_info(session, categories_id)
    except CategoriesInfoException as cie:
        raise HTTPException(**cie.__dict__)


#### Manager ####
@cbv(router)
class Manager:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/managers", response_model=PaginatedManagersInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        managers_list = get_all_managers(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": managers_list}

        return response

    # API endpoint to add a managers info to the database
    @router.post("/managers")
    def add_managers(self, managers_info: CreateAndUpdateManagers, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            managers_info = create_managers(self.session, managers_info)
            return managers_info
        except ManagersInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular managers
@router.get("/managers/", response_model=Managers)
def get_managers_info(manager_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        managers_info = get_managers_info_by_id(session, manager_id)
        return managers_info
    except ManagersInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing managers info
@router.put("/managers/", response_model=Managers)
def update_managers(managers_id: int, new_info: CreateAndUpdateManagers, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        managers_info = update_managers_info(session, managers_id, new_info)
        return managers_info
    except ManagersInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to delete a managers info from the data base
@router.delete("/managers/")
def delete_managers(managers_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_managers_info(session, managers_id)
    except ManagersInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Commercials ####
@cbv(router)
class Commercial:
    session: Session = Depends(get_db)
    
    
    # API to get the list of commercial by town
    router.mount("/static", StaticFiles(directory="static"), name="static")
    templates = Jinja2Templates(directory="templates")
    @router.get("/commercial/identity", response_class=HTMLResponse)
    async def read_commercial_card(self,  request: Request,  commercials_id: int, session: Session = Depends(get_db)):
        commercials_info = get_commercials_info_by_id(session, commercials_id)
        user_info = get_users_info_by_id(session, commercials_info.idUser)
        image_link = "https://services.position.cm" + commercials_info.imageProfil
        return self.templates.TemplateResponse("item.html", {"request": request, "id": commercials_id, "name": user_info.name, "image": image_link})
    
    
    @router.post("/commercial/generateQrCode")
    async def generate_qr_code(self, commercial_id: int, authorization:str=Header(None), session: Session = Depends(get_db)):
        if authorization is None:
                raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            create_qr(commercial_id)
            return {'message': 'Qr code  - Generated Succesfully!'}
        except Exception as cie:
            raise HTTPException(**cie.__dict__)
    
    
    # API to get the list of d info
    @router.get("/commercials", response_model=PaginatedCommercialsInfo)
    def list_all_comm(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        commercials_list = get_all_commercials(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": commercials_list}
        return response

    # API to get the list of commercial by town
    @router.get("/commercials/by_ville", response_model=PaginatedCommercialsInfo)
    def list_comm_by_ville(self, ville: str, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        commercials_list = get_all_commercials_by_ville(self.session, limit, offset, ville=ville)
        response = {"limit": limit, "offset": offset, "data": commercials_list}
        return response

    # API to get the list of commercial by town
    @router.get("/commercials/by_quartier", response_model=PaginatedCommercialsInfo)
    def list_comm_by_quartier(self, quartier: str, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        commercials_list = get_all_commercials_by_quartier(self.session, limit, offset, quartier=quartier)
        response = {"limit": limit, "offset": offset, "data": commercials_list}
        return response

    # API endpoint to add a commercials info to the database
    @router.post("/commercials")
    def add_commercials(self, commercials_info: CreateAndUpdateCommercials, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            commercials_info = create_commercials(self.session, commercials_info)
            return commercials_info
        except CommercialsInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get specific info of a particular commercials
@router.get("/commercials/", response_model=Commercials)
def get_commercials_info(commercials_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = get_commercials_info_by_id(session, commercials_id)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/commercials/statistics/count/ets/", )
def get_commercials_info(commercials_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = count_number_of_ets(session, commercials_id)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)
    
# API endpoint to get count of etablissement of a commercial
@router.get("/commercials/statistics/get/ets/by_day/", )
def get_commercials_info(commercials_id: int, day: datetime ,session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = get_ets_by_day(session, commercials_id, day=day)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/commercials/statistics/get/ets/by_week/", )
def get_commercials_info(commercials_id: int, aDayOfTheWeek: datetime, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = get_ets_by_week(session, commercials_id, day=aDayOfTheWeek)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial 
@router.get("/commercials/statistics/get/ets/by_month/", )
def get_commercials_info(commercials_id: int, month: int, year: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        x_date = datetime2.datetime(year, month, 1) # random date just to target the month
        commercials_info = get_ets_by_month(session, commercials_id, day=x_date)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get count of etablissement of a commercial
@router.get("/commercials/statistics/get/ets/by_year/", )
def get_commercials_info(commercials_id: int, year: int ,session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = get_ets_by_year(session, commercials_id, year=year)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing commercials info
@router.put("/commercials/", response_model=Commercials)
def update_commercials(commercials_id: int, new_info: CreateAndUpdateCommercials, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        commercials_info = update_commercials_info(session, commercials_id, new_info)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API endpoint to get Salary of a commercial for specific month
@router.get("/commercials/statistics/salary/", )
def get_commercials_salary(commercials_id: int, month: int, year: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        x_date = datetime2.datetime(year, month, 1) # random date just to target the month
        commercials_info = get_comm_salary(session, commercials_id, day=x_date)
        return commercials_info
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to delete a commercials info from the data base
@router.delete("/commercials/")
def delete_commercials(commercials_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_commercials_info(session, commercials_id)
    except CommercialsInfoException as cie:
        raise HTTPException(**cie.__dict__)




#### Horaires ####
@cbv(router)
class Horaire:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/horaires", response_model=PaginatedHorairesInfo)
    def list_horaires(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        horaires_list = get_all_horaires(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": horaires_list}

        return response

    # API endpoint to add a horaires info to the database
    @router.post("/horaires")
    def add_horaires(self, horaires_info: CreateAndUpdateHoraires, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            horaires_info = create_horaires(self.session, horaires_info)
            return horaires_info
        except HorairesInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular horaires
@router.get("/horaires/", response_model=Horaires)
def get_horaires_info(horaires_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        horaires_info = get_horaires_info_by_id(session, horaires_id)
        return horaires_info
    except HorairesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing horaires info
@router.put("/horaires/", response_model=Horaires)
def update_horaires(horaires_id: int, new_info: CreateAndUpdateHoraires, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        horaires_info = update_horaires_info(session, horaires_id, new_info)
        return horaires_info
    except HorairesInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a horaires info from the data base
@router.delete("/horaires/")
def delete_horaires(horaires_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_horaires_info(session, horaires_id)
    except HorairesInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Images ####
@cbv(router)
class Image:
    session: Session = Depends(get_db)
    
    def __init__(self) -> None:
        self.path_etablissement = "/images/etablissements/"
        self.path_commerciaux = "/images/commerciaux/"
        self.path_qrcodes = "/images/qrcodes/"

    # API to get the list of d info
    @router.get("/images", response_model=PaginatedImagesInfo)
    def list_images(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        images_list = get_all_images(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": images_list}

        return response

    # API endpoint to add a images info to the database
    @router.post("/images")
    def add_images(self, images_info: CreateAndUpdateImages, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            images_info = create_Images(self.session, images_info)
            return images_info
        except ImagesInfoException as cie:
            raise HTTPException(**cie.__dict__)
    
    # Images FTP FOR etablissement
    @router.post("/file/upload/etablissement")
    async def upload_file_etablissement(self, file: UploadFile = File(...), authorization:str = Header(None)):

        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        try:
            extension = file.filename.split(".")[1]
            filename = "ETS_POSITION_" + str(read_last_image_identification("etablissement")) + '.' + extension
            with open(self.path_etablissement + filename, 'wb') as image:
                content = await file.read()
                image.write(content)
                image.close()
            save_last_image_identification("etablissement")
            return JSONResponse(content={"filename": filename}, status_code=200)
        except FTPImagesException as cie:
            raise HTTPException(**cie.__dict__)
        
    @router.get("/file/download/etablissement")
    def download_file_etablissement(self, name_file: str):
        filename = self.path_etablissement + name_file
        return FileResponse(path=getcwd() + filename, media_type='application/octet-stream', filename=name_file)

    @router.get("/file/get/etablissement")
    def get_file_etablissement(self, name_file: str):
        filename = self.path_etablissement + name_file
        return FileResponse(path=getcwd() + filename)

    @router.delete("/file/delete/etablissement")
    def delete_file_etablissement(self, name_file: str, authorization:str = Header(None)):
        
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        filename = self.path_etablissement + name_file
        try:
            remove(getcwd() + filename)
            return JSONResponse(content={
                "removed": True
                }, status_code=200)   
        except FileNotFoundError:
            return JSONResponse(content={
                "removed": False,
                "error_message": "File not found"
            }, status_code=404)
       
    # Images FTP FOR commerciaux
    @router.post("/file/upload/commerciaux")
    async def upload_file_commerciaux(self, file: UploadFile = File(...), authorization:str = Header(None)):

        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        try:
            extension = file.filename.split(".")[1]
            filename = "COM_POSITION_" + str(read_last_image_identification("commerciaux")) + '.' + extension
            with open(self.path_commerciaux + filename, 'wb') as image:
                content = await file.read()
                image.write(content)
                image.close()
            save_last_image_identification("commerciaux")
            return JSONResponse(content={"filename": filename}, status_code=200)
        except FTPImagesException as cie:
            raise HTTPException(**cie.__dict__)

    # SEnd qr code image of a commercial
    @router.get("/file/get/qrcode")
    def get_qrcode(self, commercial_id:int):
        name_file = self.path_qrcodes + "/qr_" + str(commercial_id) + ".png"
        return FileResponse(path=getcwd() + name_file, media_type='application/octet-stream', filename=name_file)

    @router.get("/file/download/commerciaux")
    def download_file_commerciaux(self, name_file: str):
        filename = self.path_commerciaux + name_file
        return FileResponse(path=getcwd() + filename, media_type='application/octet-stream', filename=name_file)

    @router.get("/file/get/commerciaux")
    def get_file_commerciaux(self, name_file: str):
        filename = self.path_commerciaux + name_file
        return FileResponse(path=getcwd() + filename)

    @router.delete("/file/delete/commerciaux")
    def delete_file_commerciaux(self, name_file: str, authorization:str = Header(None)):
        
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        filename = self.path_commerciaux + name_file
        try:
            remove(getcwd() + filename)
            return JSONResponse(content={
                "removed": True
                }, status_code=200)   
        except FileNotFoundError:
            return JSONResponse(content={
                "removed": False,
                "error_message": "File not found"
            }, status_code=404)

# API endpoint to get info of a particular images
@router.get("/images/", response_model=Images)
def get_images_info(images_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        images_info = get_images_info_by_id(session, images_id)
        return images_info
    except ImagesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing images info
@router.put("/images/", response_model=Images)
def update_images(images_id: int, new_info: CreateAndUpdateImages, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        images_info = update_images_info(session, images_id, new_info)
        return images_info
    except ImagesInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a images info from the data base
@router.delete("/images/")
def delete_images(images_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_images_info(session, images_id)
    except ImagesInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Telephones ####
@cbv(router)
class Telephone:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/telephones", response_model=PaginatedTelephonesInfo)
    def list_telephones(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        telephones_list = get_all_telephones(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": telephones_list}

        return response

    # API endpoint to add a telephones info to the database
    @router.post("/telephones")
    def add_telephones(self, telephones_info: CreateAndUpdateTelephones, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            telephones_info = create_Telephones(self.session, telephones_info)
            return telephones_info
        except TelephonesInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular telephones
@router.get("/telephones/", response_model=Telephones)
def get_telephones_info(telephones_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        telephones_info = get_telephones_info_by_id(session, telephones_id)
        return telephones_info
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing telephones info
@router.put("/telephones/", response_model=Telephones)
def update_telephones(telephones_id: int, new_info: CreateAndUpdateTelephones, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        telephones_info = update_telephones_info(session, telephones_id, new_info)
        return telephones_info
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a telephones info from the data base
@router.delete("/telephones/")
def delete_telephones(telephones_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_telephones_info(session, telephones_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)



#### Batiments ####
@cbv(router)
class Batiment:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/batiments", response_model=PaginatedBatimentsInfo)
    def list_batiments(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        batiments_list = get_all_batiments(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": batiments_list}

        return response
    

    # API endpoint to add a batiments info to the database
    @router.post("/batiments")
    def add_batiments(self, batiments_info: CreateAndUpdateBatiments, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            batiments_info = create_Batiments(self.session, batiments_info)
            return batiments_info
        except BatimentsInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular batiments
@router.get("/batiments/", response_model=Batiments)
def get_batiments_info(batiments_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        batiments_info = get_batiments_info_by_id(session, batiments_id)
        return batiments_info
    except BatimentsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing batiments info
@router.put("/batiments/", response_model=Batiments)
def update_batiments(batiments_id: int, new_info: CreateAndUpdateBatiments, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        batiments_info = update_batiments_info(session, batiments_id, new_info)
        return batiments_info
    except BatimentsInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a batiments info from the data base
@router.delete("/batiments/")
def delete_batiments(batiments_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_batiments_info(session, batiments_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)


#### FailedJobs ####
@cbv(router)
class FailedJob:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/failedJobs", response_model=PaginatedFailedJobsInfo)
    def list_failedJobs(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        failedJobs_list = get_all_failedJobs(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": failedJobs_list}

        return response

    # API endpoint to add a failedJobs info to the database
    @router.post("/failedJobs")
    def add_failedJobs(self, failedJobs_info: CreateAndUpdateFailedJobs, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            failedJobs_info = create_FailedJobs(self.session, failedJobs_info)
            return failedJobs_info
        except FailedJobsInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular failedJobs
@router.get("/failedJobs/", response_model=FailedJobs)
def get_failedJobs_info(failedJobs_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        failedJobs_info = get_failedJobs_info_by_id(session, failedJobs_id)
        return failedJobs_info
    except FailedJobsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing failedJobs info
@router.put("/failedJobs/", response_model=FailedJobs)
def update_failedJobs(failedJobs_id: int, new_info: CreateAndUpdateFailedJobs, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        failedJobs_info = update_failedJobs_info(session, failedJobs_id, new_info)
        return failedJobs_info
    except FailedJobsInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a failedJobs info from the data base
@router.delete("/failedJobs/")
def delete_failedJobs(failedJobs_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_failedJobs_info(session, failedJobs_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)


#### Users ####
@cbv(router)
class FailedJob:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/users", response_model=PaginatedUsersInfo)
    def list_users(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        users_list = get_all_users(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": users_list}

        return response

    # API endpoint to add a users info to the database
    @router.post("/users")
    def add_users(self, users_info: CreateAndUpdateUsers, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            users_info = create_Users(self.session, users_info)
            return users_info
        except UsersInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular users
@router.get("/users/", response_model=Users)
def get_users_info(users_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        users_info = get_users_info_by_id(session, users_id)
        return users_info
    except UsersInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing users info
@router.put("/users/", response_model=Users)
def update_users(users_id: int, new_info: CreateAndUpdateUsers, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        users_info = update_users_info(session, users_id, new_info)
        return users_info
    except UsersInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a users info from the data base
@router.delete("/users/")
def delete_users(users_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_users_info(session, users_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)




#### Trackings ####
@cbv(router)
class FailedJob:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/trackings", response_model=PaginatedTrackingsInfo)
    def list_trackings(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        trackings_list = get_all_trackings(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": trackings_list}

        return response

    # API endpoint to add a trackings info to the database
    @router.post("/trackings")
    def add_trackings(self, trackings_info: CreateAndUpdateTrackings, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            trackings_info = create_Trackings(self.session, trackings_info)
            return trackings_info
        except TrackingsInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular trackings
@router.get("/trackings/", response_model=Trackings)
def get_trackings_info(trackings_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        trackings_info = get_trackings_info_by_id(session, trackings_id)
        return trackings_info
    except TrackingsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing trackings info
@router.put("/trackings/", response_model=Trackings)
def update_trackings(trackings_id: int, new_info: CreateAndUpdateTrackings, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        trackings_info = update_trackings_info(session, trackings_id, new_info)
        return trackings_info
    except TrackingsInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a trackings info from the data base
@router.delete("/trackings/")
def delete_trackings(trackings_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_trackings_info(session, trackings_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)


#### Zones ####
@cbv(router)
class FailedJob:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/zones", response_model=PaginateZonesInfo)
    def list_zones(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        zones_list = get_all_zones(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": zones_list}

        return response

    # API endpoint to add a zones info to the database
    @router.post("/zones")
    def add_zones(self, zones_info: CreateAndUpdateZones, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            zones_info = create_Zones(self.session, zones_info)
            return zones_info
        except ZonesInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular zones
@router.get("/zones/", response_model=Zones)
def get_zones_info(zones_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        zones_info = get_zones_info_by_id(session, zones_id)
        return zones_info
    except ZonesInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing zones info
@router.put("/zones/", response_model=Zones)
def update_zones(zones_id: int, new_info: CreateAndUpdateZones, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        zones_info = update_zones_info(session, zones_id, new_info)
        return zones_info
    except ZonesInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a zones info from the data base
@router.delete("/zones/")
def delete_zones(zones_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_zones_info(session, zones_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)


#### SousCategorieEtablissements ####
@cbv(router)
class FailedJob:
    session: Session = Depends(get_db)

    # API to get the list of d info
    @router.get("/sousCategorieEtablissements", response_model=PaginateSousCategorieEtablissementsInfo)
    def list_sousCategorieEtablissements(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])

        sousCategorieEtablissements_list = get_all_sousCategorieEtablissements(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": sousCategorieEtablissements_list}

        return response

    # API endpoint to add a sousCategorieEtablissements info to the database
    @router.post("/sousCategorieEtablissements")
    def add_sousCategorieEtablissements(self, sousCategorieEtablissements_info: CreateAndUpdateSousCategorieEtablissements, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        try:
            sousCategorieEtablissements_info = create_SousCategorieEtablissements(self.session, sousCategorieEtablissements_info)
            return sousCategorieEtablissements_info
        except SousCategorieEtablissementsInfoException as cie:
            raise HTTPException(**cie.__dict__)

# API endpoint to get info of a particular sousCategorieEtablissements
@router.get("/sousCategorieEtablissements/", response_model=SousCategorieEtablissements)
def get_sousCategorieEtablissements_info(sousCategorieEtablissements_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        sousCategorieEtablissements_info = get_sousCategorieEtablissements_info_by_id(session, sousCategorieEtablissements_id)
        return sousCategorieEtablissements_info
    except SousCategorieEtablissementsInfoException as cie:
        raise HTTPException(**cie.__dict__)

# API to update a existing sousCategorieEtablissements info
@router.put("/sousCategorieEtablissements/", response_model=SousCategorieEtablissements)
def update_sousCategorieEtablissements(sousCategorieEtablissements_id: int, new_info: CreateAndUpdateSousCategorieEtablissements, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        sousCategorieEtablissements_info = update_sousCategorieEtablissements_info(session, sousCategorieEtablissements_id, new_info)
        return sousCategorieEtablissements_info
    except SousCategorieEtablissementsInfoException as cie:
        raise HTTPException(**cie.__dict__)
                                                                                                                                   
# API to delete a sousCategorieEtablissements info from the data base
@router.delete("/sousCategorieEtablissements/")
def delete_sousCategorieEtablissements(sousCategorieEtablissements_id: int, session: Session = Depends(get_db), authorization:str = Header(None)):
    if authorization is None:
        raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
    auth_response = verify_token(authorization.split(' ')[1])
    if ('user_id' not in auth_response):
        raise HTTPException(status_code=401, detail=auth_response['message'])
    if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
        raise HTTPException(status_code=401, detail=auth_response['message'])
    try:
        return delete_sousCategorieEtablissements_info(session, sousCategorieEtablissements_id)
    except TelephonesInfoException as cie:
        raise HTTPException(**cie.__dict__)


