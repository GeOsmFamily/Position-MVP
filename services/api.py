# api.py
from fastapi import APIRouter, Depends, HTTPException, Header
from fastapi_utils.cbv import cbv
from auth import verify_token
from sqlalchemy.orm import Session
from crud import (
    delete_ets_info,
    get_all_ets_by_payment,
    get_etablissement_info_by_id,
    get_all_ets,
    create_ets,
    update_ets_info,
)
from database import get_db
from exceptions import (
    EtablissementInfoException
)
from schemas import (
    Etablissement,
    CreateAndUpdateEtablissements,
    PaginatedEtablissementInfo
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
