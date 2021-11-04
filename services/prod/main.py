# main.py
# Import FastAPI
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import uvicorn
from api import router
# Initialize the app
app = FastAPI()

app.include_router(router)

origins = [
    "127.0.0.1",
    "https://position.services.cm",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_methods=["*"],
    allow_headers=["*"],
    allow_credentials=True,
)

#TODO: define middleware to verify each header request intead of analysing in path methods(token_required)



# GET operation at route '/'
@app.get('/')
def root_api():
    return {"message": "Welcome to Position'services API"}