# api.py
from fastapi import APIRouter, Depends, HTTPException
from fastapi_utils.cbv import cbv
from sqlalchemy.orm import Session
from crud import (
    delete_ets_info,
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

router = APIRouter()


#### Etablissement ####
# Example of Class based view
@cbv(router)
class Etablissements:
    session: Session = Depends(get_db)

    # API to get the list of Etablissement info
    @router.get("/etablissements", response_model=PaginatedEtablissementInfo)
    def list_ets(self, limit: int = 10, offset: int = 0):

        etablissement_list = get_all_ets(self.session, limit, offset)
        response = {"limit": limit, "offset": offset, "data": etablissement_list}

        return response

    # API endpoint to add a Etablissement info to the database
    @router.post("/etablissement")
    def add_ets(self, ets_info: CreateAndUpdateEtablissements):

        try:
            ets_info = create_ets(self.session, ets_info)
            return ets_info
        except EtablissementInfoException as cie:
            raise HTTPException(**cie.__dict__)


# API endpoint to get info of a particular etablissement
@router.get("/etablissement/", response_model=Etablissement)
def get_ets_info(etablissement_id: int, session: Session = Depends(get_db)):
    try:
        ets_info = get_etablissement_info_by_id(session, etablissement_id)
        return ets_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)


# API to update a existing etablissement info
@router.put("/etablissement/", response_model=Etablissement)
def update_etablissement(etablissement_id: int, new_info: CreateAndUpdateEtablissements, session: Session = Depends(get_db)):

    try:
        ets_info = update_ets_info(session, etablissement_id, new_info)
        return ets_info
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)


# API to delete a etablissement info from the data base
@router.delete("/etablissement/")
def delete_etablissement(etablissement_id: int, session: Session = Depends(get_db)):

    try:
        return delete_ets_info(session, etablissement_id)
    except EtablissementInfoException as cie:
        raise HTTPException(**cie.__dict__)
