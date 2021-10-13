import uvicorn

import pytest
from starlette.testclient import TestClient

from fastapi import Depends, FastAPI, APIRouter, HTTPException, Header, Request
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from typing import List

from sqlalchemy.orm import Session
from starlette.responses import RedirectResponse

import models, schemas
from database import SessionLocal, engine

from auth import has_authority, token_required, verify_token

from datetime import datetime



models.Base.metadata.create_all(bind=engine)

app = FastAPI()
router = APIRouter()

origins = [
    '127.0.0.1',
    "https://position.services.cm",
    "http://localhost",
    "http://localhost:8080",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_methods=["*"],
    allow_headers=["*"],
    allow_credentials=True,
)

# Dependency
def get_deb():
    try:
        db = SessionLocal()
        yield db
    finally:
        db.close()

#TODO: define middleware to verify each header request intead of analysing in path methods(token_required)



# get all subscribed establismments
@app.get("/establishments/", response_model=List[schemas.Etablissements])
def show_establishments(pay:int, db: Session = Depends(get_deb), authorization:str = Header(None)):
    auth_response = verify_token(authorization.split(' ')[1])
    print(">>>>>>>>>>>>>>>>>>>>>>", pay)
    if 'user_id' not in auth_response:
        raise HTTPException(status_code=401, detail=auth_response['message'])

    if has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS'):
        records = db.query(models.Etablissements).filter_by(paid=int(pay)).all()
        return records
    else:
        raise HTTPException(status_code=404, detail="Item not found")

# Create a new etasblishment
@app.post("/establishment/create/")
def create_establishments(etasblishments: schemas.Etablissements, db: Session = Depends(get_deb), authorization:str = Header(None)):
    ets = etasblishments.__dict__ # convert to dict
    auth_response = verify_token(authorization.split(' ')[1])

    if 'user_id' not in auth_response:
        raise HTTPException(status_code=401, detail=auth_response['message'])

    if has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS'):

        reg_ets = db.query(models.Etablissements).filter(
            models.Etablissements.nom==ets['nom'],
            models.Etablissements.rue==ets['rue'],
            models.Etablissements.indication_adresse==ets['indication_adresse'],
            models.Etablissements.ville==ets['ville'],
            models.Etablissements.adresse==ets['adresse'],
            models.Etablissements.lon==ets['lon'],
            models.Etablissements.lat==ets['lat'],
            models.Etablissements.description==ets['description'],
            models.Etablissements.code_postal==ets['code_postal'],
            models.Etablissements.site_internet==ets['site_internet'],
            models.Etablissements.id_sous_categorie==ets['id_sous_categorie'],
            models.Etablissements.id_commercial==ets['id_commercial'],
            models.Etablissements.id_manager==ets['id_manager'],
            models.Etablissements.paid==ets['paid'],
            models.Etablissements.created_at==ets['created_at'],
            models.Etablissements.updated_at==ets['updated_at']
        ).first()

        print(">>>>>>>>>>>>>>>>>>>>>>>>> ", reg_ets)
        if reg_ets is not None:
            raise HTTPException(status_code=500, detail="Not Implemented - Error when running the request")

        try:
            new_etasblishments = models.Etablissements(**etasblishments.__dict__)
            db.add(new_etasblishments)
            db.commit()
            db.refresh(new_etasblishments)
            return new_etasblishments
        except :
            db.rollback()
            raise HTTPException(status_code=500, detail="Not Implemented - Error when running the request")





app.include_router(router, prefix=f"/api")

data = [("A", "A"), ("AA / A+", "AA / A+"), ("AA%20%2F%20A%2B", "AA / A+") ]
@pytest.mark.parametrize("v, expected", data)
def test_urlencoded(v, expected):
    print([r.path for r in app.routes])
    with TestClient(app) as client:
        response = client.get(f"/api/test/{v}")
        assert response.status_code == 200
        assert response.json() == expected

if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)