import axios from 'axios';

const API_URL = 'http://localhost:8000/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'auth/login', {
        email: user.email,
        password: user.password
      },{
        useCredentials: true,
        headers:{
          "Access-Control-Allow-Origin": "*",
          "Accept":"application/json",
          "Content-Type": "application/json",
        }
      })
      .then(response => {
        console.log(response.data);
        if (response.data.access_token) {
          localStorage.setItem('user', JSON.stringify(response.data.access_token));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthService();
