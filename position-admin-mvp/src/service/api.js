import axios from "axios";
import store from "../store/index";
import router from "@/router";
const api = axios.create({
  baseURL: "https://test.position.cm/api/",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    "X-Authorization":
      "sssy1jcp4yAzGt6C0IpgkTqXb9zz9wpbm2QXK92ghHeSFvnqwRRxi7j2ZZf28tDg",
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
    if (response.status === 200 || response.status === 201) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    console.log(error);
    if (error.response.status) {
      switch (error.response.status) {
        case 400:
          //do something
          break;

        case 401:
          localStorage.removeItem("user");
          router.push("/pages/login-boxed");
          break;
        case 403:
          //Set notification for unauthorized action
          /* router.replace({
            path: "/login",
            query: { redirect: router.currentRoute.fullPath },
          });*/
          break;
        case 404:
          break;
        case 502:
          setTimeout(() => {
            store
              .dispatch("auth/logout")
              .then(() => {
                router.push("/pages/login-boxed");
              })
              .catch((onerror) => {
                console.log(onerror);
                router.push("/pages/login-boxed");
              });
          }, 1000);
      }
      return Promise.reject(error.response);
    }
  }
);
export default api;
