export interface Categorie{
  id: number;
  nom: string;
  logo_url: string;
  created_at: string;
  updated_at: string;
}

export interface ListeCategorie {
  limit: number;
  offset: number;
  data: Categorie[];
}
