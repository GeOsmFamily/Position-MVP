export interface User {
  id: number;
  name: string;
  email: string;
  email_verified_at: Date;
  created_at: Date;
  updated_at: Date;
}

export interface Data {
  access_token?: string;
  user: User;
}

export interface UserInterface {
  success: boolean;
  data: Data;
  message: string;
}
