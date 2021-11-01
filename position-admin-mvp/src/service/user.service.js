import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8000/';

class UserService {

  getUserData(token){
    return axios.get(API_URL + 'auth/me',{
      headers:{
        "Access-Control-Allow-Origin": "*",
        "Accept":"application/json",
        "Content-Type": "application/json",
        "Authorization": "Bearear " + token
      }
    });
  }
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();