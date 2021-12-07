
from sqlalchemy.orm import relationship
from sqlalchemy.schema import Column
from sqlalchemy.types import String, Integer, DateTime, Boolean, Enum
from database import Base
from sqlalchemy.types import Date, Time
from sqlalchemy import ForeignKey


#### catégories models ####
class Categories(Base):
    __tablename__ = "categories"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    logoUrl = Column(String(191), index=True)
    created_at = Column(Date)
    updated_at = Column(Date)
    
    souscategories = relationship("SousCategories", back_populates="categorie")


#### sous-catégories models ####
class SousCategories(Base):
    __tablename__ = "sous_categories"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    idCategorie = Column(Integer, ForeignKey("categories.id"))
    logoUrl = Column(String(191), index=True)
    created_at = Column(Date)
    updated_at = Column(Date)
    
    categorie = relationship("Categories", back_populates="souscategories")
    etablissement = relationship("Etablissements", back_populates="souscategorie")


#### Managers models ####
class Managers(Base):
    __tablename__ = "managers"

    id = Column(Integer, primary_key=True, index=True)
    idUser = Column(Integer)
    created_at = Column(Date)
    updated_at = Column(Date)
    
    etablissement = relationship("Etablissements", back_populates="manager")


#### Commercials models ####
class Commercials(Base):
    __tablename__ = "commercials"

    id = Column(Integer, primary_key=True, index=True)
    idUser = Column(Integer)
    numeroCni = Column(String(191), index=True)
    numeroBadge = Column(Integer)
    ville = Column(String(191), index=True)
    quartier = Column(String(191), index=True)
    imageProfil = Column(String(191), index=True)
    idZone = Column(String(191), index=True)
    actif = Column(Integer)
    created_at = Column(Date)
    updated_at = Column(Date)
    etablissement = relationship("Etablissements", back_populates="commercial")

#### Batiments models ####
class Batiments(Base):
    __tablename__ = "batiments"

    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), index=True)
    nombreNiveaux = Column(Integer)
    codeBatiment = Column(String(191), index=True)
    longitude = Column(String(191), index=True)
    latitude = Column(String(191), index=True)
    image = Column(String(191), index=True)
    indication = Column(String(191), index=True)
    rue = Column(String(191), index=True)
    ville = Column(String(191), index=True)
    commune = Column(String(191), index=True)
    quartier = Column(String(191), index=True)
    created_at = Column(Date)
    updated_at = Column(Date)
    
    etablissement = relationship("Etablissements", back_populates="batiment")
    
#### FailedJobs models ####
class FailedJobs(Base):
    __tablename__ = "failed_jobs"

    id = Column(Integer, primary_key=True, index=True)
    uuid = Column(String(191), index=True)
    connection = Column(Integer)
    queue = Column(String(191), index=True)
    payload = Column(String(191), index=True)
    exception = Column(String(191), index=True)
    failed_at = Column(Date)

#### Horaires models ####
class Horaires(Base):
    __tablename__ = "horaires"

    id = Column(Integer, primary_key=True, index=True)
    idEtablissement = Column(Integer, ForeignKey('etablissements.id'))
    jour = Column(String(191), index=True)
    ouvert = Column(Integer)
    heureOuverture = Column(Time)
    heureFermeture = Column(Time)
    created_at = Column(Date)
    updated_at = Column(Date)
    
    etablissement = relationship("Etablissements", back_populates="horaires")

#### Images des etalissements ####
class Images(Base):
    __tablename__ = "images"

    id = Column(Integer, primary_key=True, index=True)
    idEtablissement = Column(Integer, ForeignKey('etablissements.id'))
    imageUrl = Column(String(191))
    created_at = Column(Date)
    updated_at = Column(Date)
    
    etablissement = relationship("Etablissements", back_populates="images")

#### Telephones des etalissements ####
class Telephones(Base):
    __tablename__ = "telephones"

    id = Column(Integer, primary_key=True, index=True)
    idEtablissement = Column(Integer, ForeignKey('etablissements.id'))
    numero = Column(String(191))
    whatsapp = Column(String(191))
    created_at = Column(Date)
    updated_at = Column(Date)
    
    etablissement = relationship("Etablissements", back_populates="telephones")

#### Etablissements models ####
class Etablissements(Base):
    __tablename__ = "etablissements"

    id = Column(Integer, primary_key=True, index=True)
    idBatiment = Column(Integer, ForeignKey('batiments.id')) # Foreign key
    nom = Column(String(191), index=True)
    indicationAdresse = Column(String(191), index=True)
    codePostal = Column(String(191), index=True)
    siteInternet = Column(String(191), index=True)
    idSousCategorie = Column(Integer, ForeignKey('sous_categories.id')) # Foreign key
    idCommercial = Column(Integer, ForeignKey('commercials.id')) # Foreign key
    idManager = Column(Integer, ForeignKey('managers.id')) # Foreign key
    etage = Column(Integer)
    cover = Column(String(191))
    vues = Column(Integer)
    created_at = Column(Date)
    updated_at = Column(Date)
    description = Column(String(), index=True)
    
    telephones = relationship("Telephones", back_populates="etablissement")
    images = relationship("Images", back_populates="etablissement")
    horaires = relationship("Horaires", back_populates="etablissement")
    batiment = relationship("Batiments", back_populates="etablissement")
    souscategorie = relationship("SousCategories", back_populates="etablissement")
    commercial = relationship("Commercials", back_populates="etablissement")
    manager = relationship("Managers", back_populates="etablissement")
    

#### User models ####
class Users(Base):
    __tablename__ = "users"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(191), index=True)
    email = Column(String(191), index=True)
    email_verified_at = Column(Date)
    password = Column(String())
    phone = Column(String(191))
    role = Column(Integer)
    remember_token = Column(String(191))
    created_at = Column(Date)
    updated_at = Column(Date)
    

### Tracking ####
class Users(Base):
    __tablename__ = "trackings"
    id = Column(Integer, primary_key=True, index=True)
    idUser = Column(String(191), ForeignKey('users.id'))
    longitude = Column(String(191))
    latitude = Column(Date)
    created_at = Column(Date)
    updated_at = Column(Date)
    
#### Zones ###
class Users(Base):
    __tablename__ = "zones"
    id = Column(Integer, primary_key=True, index=True)
    nom = Column(String(191), ForeignKey('users.id'))
    ville = Column(String(191))
    created_at = Column(Date)
    updated_at = Column(Date)
    
    
# TODO: crud de telephones, tracking, zones