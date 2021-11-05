import UserService from '../service/user.service';

const initialState = {user: null};

export const user = {
  namespaced: true,
  state: initialState,
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
      state.user = data;
    },
    userFailure(state){
      state.user = null;
    }
  }
};
