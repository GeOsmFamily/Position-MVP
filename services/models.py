
from sqlalchemy.schema import Column
from sqlalchemy.types import String, Integer, DateTime, Boolean, Enum
from database import Base
from sqlalchemy.types import Date


#### Etablissements models ####
class Etablissements(Base):
    __tablename__ = "etablissements"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    rue = Column(String(191), index=True)
    indication_adresse = Column(String(191), index=True)
    ville = Column(String(191), index=True)
    adresse = Column(String(191), index=True)
    lon = Column(String(191), index=True)
    lat = Column(String(191), index=True)
    description = Column(String(191), index=True)
    code_postal = Column(String(191), index=True)
    site_internet = Column(String(191), index=True)
    id_sous_categorie = Column(Integer)
    id_commercial = Column(Integer)
    id_manager = Column(Integer)
    paid = Column(Integer)
    created_at = Column(Date)
    updated_at = Column(Date)


#### sous-catégories models ####
class SousCategories(Base):
    __tablename__ = "sous_categories"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    id_categorie = Column(String(191), index=True)
    created_at = Column(Date)
    updated_at = Column(Date)


#### catégories models ####
class Categories(Base):
    __tablename__ = "categories"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    logo_url = Column(String(191), index=True)
    created_at = Column(Date)
    updated_at = Column(Date)
