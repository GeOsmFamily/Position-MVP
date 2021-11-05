import CategoryService from '../service/category.service';

const initialState =  { loading: null, categories: null, currentCategory: null,subCategory:null};

export const category = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchCategories({commit,rootState}){
      commit('toggleLoading',true);
      return CategoryService.getListCategories(rootState.auth.token).then(
        categories => {
          console.log(categories.data.data);
          commit('toggleLoading',false);
          commit('categoriesSuccess',categories);
          return Promise.resolve(categories);
          },
      error => {
          commit('toggleLoading',false);
          commit('categoriesFailure');
          return Promise.reject(error);
      });
    },
  },
  mutations: {
    categoriesSuccess(state,categories){
      state.categories = categories;
    },
    categoriesFailure(state){
      state.categories = null;
    },
    toggleLoading(state,value){
      state.loading = value;
    }
  }
};
