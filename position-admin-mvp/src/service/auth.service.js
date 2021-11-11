import api from "./api";

class AuthService {
  login(user) {
    return api
      .post("auth/login", {
        email: user.email,
        password: user.password,
      })
      .then((response) => {
        console.log(response.data);
        if (response.data.data.token) {
          localStorage.setItem(
            "user",
            JSON.stringify(response.data.data.token)
          );
        }
        return response.data.data;
      });
  }

  logout(token) {
    console.log(token);
    return api.get("auth/logout ").then((response) => {
      localStorage.removeItem("user");
      return response.data;
    });
  }

  register(user) {
    return api.post("signup", {
      username: user.username,
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthService();
