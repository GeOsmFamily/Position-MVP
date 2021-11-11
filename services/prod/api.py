# api.py
from exceptions import (CategoriesInfoException, CommercialsInfoException,
                        EtablissementInfoException, FTPImagesException, HorairesInfoException,
                        ImagesInfoException, ManagersInfoException,
                        SousCategoriesInfoException, TelephonesInfoException)

from fastapi import APIRouter, Depends, Header, HTTPException
from fastapi_utils.cbv import cbv
from sqlalchemy.orm import Session

from auth import has_authority, verify_token
from crud import (create_categories, create_commercials, create_ets,
                  create_horaires, create_Images, create_managers,
                  create_souscategories, create_Telephones,
                  delete_categories_info, delete_commercials_info,
                  delete_ets_info, delete_horaires_info, delete_images_info,
                  delete_managers_info, delete_souscategories_info,
                  delete_telephones_info, get_all_categories,
                  get_all_commercials, get_all_commercials_by_quartier,
                  get_all_commercials_by_town, get_all_ets,
                  get_all_ets_by_payment, get_all_horaires, get_all_images,
                  get_all_managers, get_all_souscategories, get_all_telephones,
                  get_categories_info_by_id, get_commercials_info_by_id,
                  get_etablissement_info_by_id, get_images_info_by_id,
                  get_managers_info_by_id, get_souscategories_info_by_id,
                  get_telephones_info_by_id, update_categories_info,
                  update_commercials_info, update_ets_info,
                  update_horaires_info, update_images_info,
                  update_managers_info, update_souscategories_info,
                  update_telephones_info)
from database import get_db
from schemas import (Categories, Commercials, CreateAndUpdateCategories,
                     CreateAndUpdateCommercials, CreateAndUpdateEtablissements,
                     CreateAndUpdateHoraires, CreateAndUpdateImages,
                     CreateAndUpdateManagers, CreateAndUpdateSousCategories,
                     CreateAndUpdateTelephones, Etablissement, Horaires,
                     Images, Managers, PaginatedCategoriesInfo,
                     PaginatedCommercialsInfo, PaginatedEtablissementInfo,
                     PaginatedHorairesInfo, PaginatedImagesInfo,
                     PaginatedManagersInfo, PaginatedSousCategoriesInfo,
                     PaginatedTelephonesInfo, SousCategories, Telephones)

from fastapi import UploadFile, File
from os import getcwd, remove
from fastapi.responses import FileResponse, JSONResponse

from utils import read_last_image_identification, save_last_image_identification

router = APIRouter()


#### Etablissement ####
@cbv(router)
class Etablissements:
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
@router.get("/etablissement/", response_model=Etablissement)
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
@router.put("/etablissement/", response_model=Etablissement)
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

    # API to get the list of d info
    @router.get("/commercials", response_model=PaginatedCommercialsInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
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
    @router.get("/commercials/by_town", response_model=PaginatedCommercialsInfo)
    def list_ets(self, town: str, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
        if authorization is None:
            raise HTTPException(500, {'message': 'DecodeError - Token is invalid!'})
        auth_response = verify_token(authorization.split(' ')[1])
        if ('user_id' not in auth_response):
            raise HTTPException(status_code=401, detail=auth_response['message'])
        if (has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS')) is False:
            raise HTTPException(status_code=401, detail=auth_response['message'])
        commercials_list = get_all_commercials_by_town(self.session, limit, offset, town=town)
        response = {"limit": limit, "offset": offset, "data": commercials_list}
        return response

    # API to get the list of commercial by town
    @router.get("/commercials/by_quartier", response_model=PaginatedCommercialsInfo)
    def list_ets(self, quartier: str, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
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

# API endpoint to get info of a particular commercials
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
