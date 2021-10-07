from typing import List

import uvicorn

from fastapi import Depends, FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session
from starlette.responses import RedirectResponse

import models, schemas
from database import SessionLocal, engine

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
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
        

@app.get("/")
def main():
    return RedirectResponse(url="/docs")

@app.get("/establishment/", response_model=List[schemas.Etablissements])
def show_establishments(db: Session = Depends(get_deb)):
    records = db.query(models.Etablissements).all()
    return records

if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)