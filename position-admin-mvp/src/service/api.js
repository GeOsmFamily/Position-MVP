import axios from "axios";
import router from "@/router";

const api = axios.create({
  baseURL: "https://services.position.cm/api/",
  timeout: 1000,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});
api.interceptors.request.use(
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
api.interceptors.response.use(
  (response) => {
    console.log("interceptor");
    if (response.status === 200 || response.status === 201) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    if (error.response.status) {
      switch (error.response.status) {
        case 400:
          //do something
          break;

        case 401:
          alert("session expired");
          break;
        case 403:
          router.replace({
            path: "/login",
            query: { redirect: router.currentRoute.fullPath },
          });
          break;
        case 404:
          break;
        case 502:
          setTimeout(() => {
            router.replace({
              path: "/login",
              query: {
                redirect: router.currentRoute.fullPath,
              },
            });
          }, 1000);
      }
      return Promise.reject(error.response);
    }
  }
);
export default api;
