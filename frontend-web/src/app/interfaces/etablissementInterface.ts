  export interface Batiment {
      id: number;
      nom: string;
      nombreNiveaux: number;
      codeBatiment: string;
      longitude: number;
      latitude: number;
      image: string;
      indication?: any;
      rue: string;
      ville: string;
      commune: string;
      quartier: string;
      created_at: Date;
      updated_at: Date;
  }

  export interface Pivot {
      idEtablissement: number;
      idSousCategorie: number;
  }

  export interface Categorie {
      id: number;
      nom: string;
      logoUrl?: any;
      created_at: Date;
      updated_at: Date;
  }

  export interface SousCategory {
      id: number;
      nom: string;
      idCategorie: number;
      logoUrl?: any;
      created_at: Date;
      updated_at: Date;
      pivot: Pivot;
      categorie: Categorie;
  }

  export interface Image {
      id: number;
      idEtablissement: string;
      imageUrl: string;
      created_at: Date;
      updated_at: Date;
  }

  export interface Horaire {
      id: number;
      idEtablissement: number;
      jour: string;
      ouvert: number;
      heureOuverture: string;
      heureFermeture: string;
      created_at: Date;
      updated_at: Date;
  }

  export interface Telephone {
      id: number;
      idEtablissement: number;
      numero: string;
      whatsapp: number;
      created_at: Date;
      updated_at: Date;
      principal: number;
  }

  export interface Commercial {
      id: number;
      idUser: number;
      numeroCni: number;
      numeroBadge: number;
      ville: string;
      quartier: string;
      imageProfil: string;
      idZone: number;
      actif: number;
      sexe: string;
      whatsapp: string;
      diplome: string;
      tailleTshirt: string;
      age: number;
      created_at: Date;
      updated_at: Date;
  }

  export interface Data {
      id: number;
      idBatiment: number;
      nom: string;
      indicationAdresse?: any;
      codePostal?: any;
      siteInternet?: any;
      idCommercial: number;
      idManager?: any;
      etage: number;
      autres: string;
      cover: string;
      vues: number;
      created_at: Date;
      updated_at: Date;
      description?: any;
      nomCommercial: string;
      batiment: Batiment;
      sous_categories: SousCategory[];
      images: Image[];
      horaires: Horaire[];
      telephones: Telephone[];
      commercial: Commercial;
  }

  export interface Etablissement{
      success: boolean;
      data: Data;
      message: string;
  }



