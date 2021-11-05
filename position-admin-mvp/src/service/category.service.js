import axios from "axios";

const API_URL = 'https://cors-anywhere.herokuapp.com/https://api.position.cm/';

class CategoryService {

  getListCategories(token){
    console.log(token);
    return axios.get(API_URL + 'categories',{
      //crossDomain: true,
      headers:{
        "Access-Control-Allow-Origin": "*",
        "Accept":"application/json",
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });
  }
}

export default new CategoryService();
