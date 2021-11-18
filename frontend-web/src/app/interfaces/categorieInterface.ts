export interface Categorie{
  id: number;
  nom: string;
  logo_url: string;
  created_at: string;
  updated_at: string;
}

export interface ListeCategorie {
  success: boolean;
  data: Categorie[];
  message: string;
}
