import api from "./api";

class UserService {
  getUserData() {
    return api.get("user/me");
  }
}

export default new UserService();
