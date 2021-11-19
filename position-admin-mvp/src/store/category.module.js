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
    fetchCategories({ commit }) {
      commit("toggleLoading", true);
      return CategoryService.getListCategories().then(
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
    createCategory({ dispatch, commit }, data) {
      commit("toggleLoading", true);
      return CategoryService.createCategory(data).then(
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
      return CategoryService.deleteCategory(id).then(
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
    categoriesSuccess(state, categories) {
      state.categories = categories.map((category) => {
        category.created_at = new Date(category.created_at).toLocaleDateString(
          "fr-FR",
          { year: "numeric", month: "numeric", day: "numeric" }
        );
        category.updated_at = new Date(category.updated_at).toLocaleDateString(
          "fr-FR",
          { year: "numeric", month: "numeric", day: "numeric" }
        );
        return category;
      });
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
