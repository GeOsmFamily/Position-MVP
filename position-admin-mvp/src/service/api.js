import axios from "axios";
export default axios.create({
  baseURL: "https://services.position.cm/",
  headers: {
    "Accept": "application/json",
    "Content-Type": "application/json"
  }
})