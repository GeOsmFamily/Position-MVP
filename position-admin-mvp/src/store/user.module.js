import UserService from '../service/user.service';

const userData = {user: null};

export const user = {
  namespaced: true,
  state: userData,
  actions: {
    getUser({commit,context},userData){
      console.log(context.rootState.auth.user);
      return UserService.getUserData().then(userData => {
        commit('userSuccess',userData);
        return Promise.resolve(userData);
      },
        error => {
        commit('userFailure');
        return Promise.reject(error);
        }
      );
    },
    login({ commit }, user) {
      return AuthService.login(user).then(
        user => {
          commit('loginSuccess', user);
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
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