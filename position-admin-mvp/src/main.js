import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, BootstrapVueIcons, ToastPlugin } from "bootstrap-vue";
import Vuelidate from "vuelidate";
import Default from "./Layout/Wrappers/baseLayout.vue";
import Pages from "./Layout/Wrappers/pagesLayout.vue";
import api from "./service/api";
import VueDataTables from "vue-data-tables";
import Element from "element-ui";
import VueGoodTablePlugin from "vue-good-table";
import VueAxios from "vue-axios";
import VueTelInput from "vue-tel-input";
import "vue-tel-input/dist/vue-tel-input.css";

Vue.use(VueTelInput); // Define default global options here (optional)
Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(ToastPlugin);
Vue.use(Vuelidate);
Vue.use(VueAxios, api);
Vue.use(Element);
Vue.use(VueDataTables);
Vue.use(VueGoodTablePlugin);
Vue.component("default-layout", Default);
Vue.component("userpages-layout", Pages);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
