import UserService from '../service/user.service';

const userData = {user: null};

export const user = {
  namespaced: true,
  state: userData,
  actions: {
    getUser({commit}){
      return UserService.getUserData(JSON.parse(localStorage.getItem('user'))).then(data => {
        commit('userSuccess',data.data);
        return Promise.resolve(data.data);
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