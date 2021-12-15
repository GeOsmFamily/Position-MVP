import CommercialService from "../service/commercial.service";

const initialState = {
  loading: null,
  commerciaux: null,
  currentCommercial: null,
};

export const commercial = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchCommerciaux({ commit }) {
      commit("toggleLoading", true);
      return CommercialService.getCommerciaux().then(
        (commerciaux) => {
          commit("toggleLoading", false);
          commit("commerciauxSuccess", commerciaux.data.data);
          return Promise.resolve(commerciaux);
        },
        (error) => {
          commit("toggleLoading", false);
          commit("commerciauxFailure");
          return Promise.reject(error);
        }
      );
    },
    setCurrentCommercial({ commit }, commercial) {
      commit("setCurrentCommercial", commercial);
    },
    createCommercial({ dispatch, commit }, data) {
      commit("toggleLoading", true);
      return CommercialService.saveCommercial(data).then(
        (result) => {
          console.log(data);
          commit("toggleLoading", false);
          dispatch("fetchCommerciaux");
          return Promise.resolve(result);
        },
        (error) => {
          commit("toggleLoading", false);
          return Promise.reject(error);
        }
      );
    },
    deleteCommercial({ dispatch }, id) {
      return CommercialService.deleteCommercial(id).then(
        (result) => {
          dispatch("fetchCommerciaux");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
    editCommercial({ dispatch }, data) {
      return CommercialService.updateCommercial(data.id, data.category).then(
        (result) => {
          dispatch("fetchCommerciaux");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    commerciauxSuccess(state, commerciaux) {
      state.commerciaux = commerciaux;
    },
    commerciauxFailure(state) {
      state.commerciaux = null;
    },
    toggleLoading(state, value) {
      state.loading = value;
    },
    setCurrentCommercial(state, value) {
      state.currentCommercial = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    commerciaux: ({ commerciaux }) => {
      return commerciaux;
    },
    commercial: ({ currentCommercial }) => {
      return currentCommercial;
    },
  },
};
