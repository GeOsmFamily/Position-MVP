# api.py
from fastapi import APIRouter, Depends, HTTPException, Header
from fastapi_utils.cbv import cbv
from auth import verify_token
from sqlalchemy.orm import Session
from crud import (
    create_commercials,
    create_managers,
    create_souscategories,
    delete_categories_info,
    delete_commercials_info,
    delete_ets_info,
    delete_managers_info,
    delete_souscategories_info,
    get_all_categories,
    get_all_commercials,
    get_all_ets_by_payment,
    get_all_managers,
    get_all_souscategories,
    get_categories_info_by_id,
    get_commercials_info_by_id,
    get_etablissement_info_by_id,
    get_all_ets,
    create_ets,
    create_categories,
    get_managers_info_by_id,
    get_souscategories_info_by_id,
    update_categories_info,
    update_commercials_info,
    update_ets_info,
    update_managers_info,
    update_souscategories_info,
)
from database import get_db
from exceptions import (
    CategoriesInfoException,
    CommercialsInfoException,
    EtablissementInfoException,
    ManagersInfoException,
    SousCategoriesInfoException
)
from schemas import (
    Categories,
    Commercials,
    CreateAndUpdateCategories,
    CreateAndUpdateCommercials,
    CreateAndUpdateManagers,
    Etablissement,
    CreateAndUpdateEtablissements,
    Managers,
    PaginatedCategoriesInfo,
    PaginatedCommercialsInfo,
    PaginatedEtablissementInfo,
    PaginatedManagersInfo,
    SousCategories,
    CreateAndUpdateSousCategories,
    PaginatedSousCategoriesInfo,
)
from auth import verify_token, has_authority

router = APIRouter()


#### Etablissement ####
# Example of Class based view
@cbv(router)
class Etablissements:
    session: Session = Depends(get_db)

    # API to get the list of Etablissement info
    @router.get("/etablissements", response_model=PaginatedEtablissementInfo)
    def list_ets(self, limit: int = 10, offset: int = 0, authorization:str = Header(None)):
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
# Example of Class based view
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
# Example of Class based view
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
# Example of Class based view
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
# Example of Class based view
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

