import CategoryService from "../service/category.service";

const initialState = {
  loading: null,
  categories: null,
  currentCategory: null,
  subCategory: null,
};

export const category = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchCategories({ commit, rootState }) {
      commit("toggleLoading", true);
      return CategoryService.getListCategories(rootState.auth.token).then(
        (categories) => {
          commit("toggleLoading", false);
          commit("categoriesSuccess", categories.data.data);
          return Promise.resolve(categories);
        },
        (error) => {
          commit("toggleLoading", false);
          commit("categoriesFailure");
          return Promise.reject(error);
        }
      );
    },
    createCategory({ commit, rootState }, data) {
      commit("toggleLoading", true);
      return CategoryService.createCategory(rootState.auth.token, data).then(
        (result) => {
          console.log(data);
          commit("toggleLoading", false);
          return Promise.resolve(result);
        },
        (error) => {
          commit("toggleLoading", false);
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    categoriesSuccess(state, categories) {
      state.categories = categories;
    },
    categoriesFailure(state) {
      state.categories = null;
    },
    toggleLoading(state, value) {
      state.loading = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    categories: ({ categories }) => {
      return categories;
    },
  },
};
