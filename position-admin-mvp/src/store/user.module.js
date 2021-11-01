import UserService from '../service/user.service';

const userData = {user: null};

export const user = {
  namespaced: true,
  state: userData,
  actions: {
    getUser({commit}){
      return UserService.getUserData(JSON.parse(localStorage.getItem('user'))).then(data => {
        console.log(data);
        commit('userSuccess',data);
        return Promise.resolve(data);
      },
        error => {
        commit('userFailure');
        return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    userSuccess(state,data) {
      state.userData.user = data;
    },
    userFailure(state){
      state.userData.user = null;
    }
  }
};