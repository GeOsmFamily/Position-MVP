import BusinessService from "../service/business.service";

const initialState = {
  loading: null,
  businesses: null,
  currentBusiness: null,
};

export const business = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchBusinesses({ commit }) {
      commit("toggleLoading", true);
      return BusinessService.getListBusiness().then(
        (businesses) => {
          commit("toggleLoading", false);
          commit("businessesSuccess", businesses.data.data);
          return Promise.resolve(businesses);
        },
        (error) => {
          commit("toggleLoading", false);
          commit("businessFailure");
          return Promise.reject(error);
        }
      );
    },
    createCategory({ dispatch, commit }, data) {
      commit("toggleLoading", true);
      return BusinessService.createBusiness(data).then(
        (result) => {
          console.log(data);
          commit("toggleLoading", false);
          dispatch("fetchCategories");
          return Promise.resolve(result);
        },
        (error) => {
          commit("toggleLoading", false);
          return Promise.reject(error);
        }
      );
    },
    deleteCategory({ dispatch }, id) {
      return BusinessService.deleteBusiness(id).then(
        (result) => {
          dispatch("fetchCategories");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
    editCategory({ dispatch }, data) {
      return BusinessService.editBusiness(data.id, data.category).then(
        (result) => {
          dispatch("fetchCategories");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    businessesSuccess(state, businesses) {
      state.businesses = businesses.map((business) => {
        business.created_at = new Date(business.created_at).toLocaleDateString(
          "fr-FR",
          { year: "numeric", month: "numeric", day: "numeric" }
        );
        business.updated_at = new Date(business.updated_at).toLocaleDateString(
          "fr-FR",
          { year: "numeric", month: "numeric", day: "numeric" }
        );
        return business;
      });
    },
    businessFailure(state) {
      state.businesses = null;
    },
    toggleLoading(state, value) {
      state.loading = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    businesses: ({ businesses }) => {
      return businesses;
    },
  },
};
