import os

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from pathlib import Path
from dotenv import load_dotenv

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent
#load env file
load_dotenv(os.path.join(BASE_DIR,'.env'))

#load env file

# SQLALCHYME_DATABASE_URL = os.environ['DATABASE_URL']
SQLALCHYME_DATABASE_URL = os.getenv('DATABASE_URL')
print(SQLALCHYME_DATABASE_URL)

engine = create_engine(SQLALCHYME_DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()