import axios from "axios";

const stat = axios.create({
  baseURL: "https://api.position.cm/",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    "X-Authorization":
      "Uf51LFMwxxcnFEhP0dLItN03Ok78snVjqHgqLU8OsBdVFxpzfz9dj8zcKGYBrkO2",
  },
});

stat.interceptors.request.use(
  (config) => {
    const token = JSON.parse(localStorage.getItem("user"));
    if (token) {
      config.headers.common["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
export default stat;
