import axios from "axios";

const API_URL = "https://cors-anywhere.herokuapp.com/https://api.position.cm/";

class CategoryService {
  getListCategories(token) {
    console.log(token);
    return axios.get(API_URL + "categories?limit=50", {
      headers: {
        "Access-Control-Allow-Origin": "*",
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    });
  }
  createCategory(token, data) {
    console.log(token);
    return axios.post(API_URL + "categories", data, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    });
  }
}

export default new CategoryService();
