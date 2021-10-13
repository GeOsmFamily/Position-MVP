import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Vue from "vue";
import BootstrapVue from "bootstrap-vue";
import Default from "./Layout/Wrappers/baseLayout.vue";
import Pages from "./Layout/Wrappers/pagesLayout.vue";

Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.component("default-layout", Default);
Vue.component("userpages-layout", Pages);

createApp(App).use(store).use(router).mount("#app");
