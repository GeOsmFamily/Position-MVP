import api from "./api";

class UserService {
  getUserData(token) {
    console.log(token);
    return api.get("user/me");
  }
  getPublicContent() {
    return api.get("all");
  }

  getUserBoard() {
    return api.get("user");
  }

  getModeratorBoard() {
    return api.get("mod");
  }

  getAdminBoard() {
    return api.get("admin");
  }
}

export default new UserService();
