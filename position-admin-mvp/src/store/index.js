import Vue from "vue";
import Vuex from "vuex";
import { auth } from "./auth.module";
import { user } from "./user.module";
import { category } from "./category.module";
import { commercial } from "./commercial.module";
import { zone } from "./zone.module";
import { stat } from "./stat.module";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    user,
    category,
    commercial,
    zone,
    stat,
  },
});
