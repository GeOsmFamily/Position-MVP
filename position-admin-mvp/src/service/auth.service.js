import axios from 'axios';

const API_URL = 'https://cors-anywhere.herokuapp.com/https://services.position.cm/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'auth/login', {
        email: user.email,
        password: user.password
      },{
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

  logout(token) {
    return axios.post(API_URL+"auth/logout ",{},{
      crossDomain: true,
      headers:{
        "Access-Control-Allow-Origin": "*",
        "Accept":"application/json",
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    }).then(response => {
      localStorage.removeItem('user');
      return response.data;
    });
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
