# api.py
from fastapi import APIRouter, Depends, HTTPException, Header
from fastapi_utils.cbv import cbv
from auth import verify_token
from sqlalchemy.orm import Session
from crud import (
    create_souscategories,
    delete_categories_info,
    delete_ets_info,
    delete_souscategories_info,
    get_all_categories,
    get_all_ets_by_payment,
    get_all_souscategories,
    get_categories_info_by_id,
    get_etablissement_info_by_id,
    get_all_ets,
    create_ets,
    create_categories,
    get_souscategories_info_by_id,
    update_categories_info,
    update_ets_info,
    update_souscategories_info,
)
from database import get_db
from exceptions import (
    CategoriesInfoException,
    EtablissementInfoException,
    SousCategoriesInfoException
)
from schemas import (
    Categories,
    CreateAndUpdateCategories,
    Etablissement,
    CreateAndUpdateEtablissements,
    PaginatedCategoriesInfo,
    PaginatedEtablissementInfo,
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
