from typing import List, Optional

import uvicorn

from fastapi import Depends, FastAPI, HTTPException, Header, Request
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session
from starlette.responses import RedirectResponse

import models, schemas
from database import SessionLocal, engine

from auth import has_authority, token_required, verify_token

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

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

#TODO: define middleware to verify each header 
# request intead of analysing in path methods(token_required)

@app.get("/")
def main():
    return RedirectResponse(url="/docs")

@app.get("/establishment/", response_model=List[schemas.Etablissements])
def show_establishments(db: Session = Depends(get_deb), authorization:str = Header(None)):
    auth_response = verify_token(authorization.split(' ')[1])
    if 'user_id' not in auth_response:
        raise HTTPException(status_code=401, detail=auth_response['message'])

    if has_authority(roles=auth_response['roles_id'], access_type='r',target='ETS'):
        records = db.query(models.Etablissements).all()
        return records
    else:
        raise HTTPException(status_code=404, detail="Item not found")

if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)