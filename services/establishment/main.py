import uvicorn

import pytest
from starlette.testclient import TestClient

from fastapi import Depends, FastAPI, APIRouter, HTTPException, Header, Request
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

from sqlalchemy.orm import Session
from starlette.responses import RedirectResponse

import models, schemas
from database import SessionLocal, engine
from models import Etablissements

from auth import has_authority, token_required, verify_token

from typing import List



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
@app.post("/establishment/create")
def create_establishments(etablissements: schemas.Etablissements, db: Session = Depends(get_deb), authorization:str = Header(None)):
    auth_response = verify_token(authorization.split(' ')[1])
    if 'user_id' not in auth_response:
        raise HTTPException(status_code=401, detail=auth_response['message'])

    if has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS'):
        records = db.query(models.Etablissements).add_column(etablissements)
        ets = schemas.Etablissements(nom="", rue="", indication_adresse="", ville="",
                                     adresse="", lon="", lat="", description="", code_postal="", 
                                     site_internet="", id_sous_categorie=1, id_commercial=1, 
                                     id_manager=1, paid=1, created_at="", updated_at="")
        db_etablissements = models.Etablissements(nom=ets.nom, rue=ets.rue, indication_adresse=ets.indication_adresse, ville=ets.ville,
                                     adresse=ets.adresse, lon=ets.lon, lat=ets.lat, description=ets.description, code_postal=ets.code_postal,
                                     site_internet=ets.site_internet, id_sous_categorie=ets.id_sous_categorie, id_commercial=ets.id_commercial,
                                     id_manager=ets.id_manager, paid=ets.paid, created_at=ets.created_at, updated_at=ets.updated_at)
        db.add(db_etablissements)
        try:
            db.commit()
        except:
            db.rollback()
            raise HTTPException(status_code=500, detail="Not Implemented - Error when creating the request")

        db.close()






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