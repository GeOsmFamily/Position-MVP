import axios from "axios";
import authHeader from './auth.header';

const API_URL = 'https://cors-anywhere.herokuapp.com/https://services.position.cm/';

class UserService {

  getUserData(token){
    return axios.get(API_URL + 'auth/me',{
      crossDomain: true,
      headers:{
        "Access-Control-Allow-Origin": "*",
        "Accept":"application/json",
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
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