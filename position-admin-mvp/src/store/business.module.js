import BusinessService from "../service/business.service";

const initialState = {
  loading: null,
  monthLoading: null,
  weekLoading: null,
  businesses: null,
  monthBusinesses: null,
  weekBusinesses: null,
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
    fetchMonthBusinesses({ commit }, data) {
      commit("toggleMonthLoading", true);
      return BusinessService.getMonthBusinness(data.month, data.year).then(
        (businesses) => {
          commit("toggleMonthLoading", false);
          commit("monthBusinessesSuccess", businesses.data);
          return Promise.resolve(businesses);
        },
        (error) => {
          commit("toggleMonthLoading", false);
          commit("monthBusinessFailure");
          return Promise.reject(error);
        }
      );
    },
    fetchWeekBusinesses({ commit }, dayOfWeek) {
      commit("toggleWeekLoading", true);
      return BusinessService.getWeekBusinesses(dayOfWeek).then(
        (businesses) => {
          commit("toggleWeekLoading", false);
          commit("weekBusinessesSuccess", businesses.data);
          return Promise.resolve(businesses);
        },
        (error) => {
          commit("toggleWeekLoading", false);
          commit("weekBusinessFailure");
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    monthBusinessesSuccess(state, businesses) {
      state.monthBusinesses = businesses;
    },

    monthBusinessesFailure(state) {
      state.monthBusinesses = null;
    },
    weekBusinessesSuccess(state, businesses) {
      state.weekBusinesses = businesses;
    },
    weekBusinessesFailure(state) {
      state.weekBusinesses = null;
    },
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
    toggleMonthLoading(state, value) {
      state.monthLoading = value;
    },
    toggleWeekLoading(state, value) {
      state.weekLoading = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    businesses: ({ businesses }) => {
      return businesses;
    },
    monthLoading: ({ monthLoading }) => {
      return monthLoading;
    },
    monthBusinesses: ({ monthBusinesses }) => {
      return monthBusinesses;
    },
    weekLoading: ({ weekLoading }) => {
      return weekLoading;
    },
    weekBusinesses: ({ weekBusinesses }) => {
      return weekBusinesses;
    },
  },
};
