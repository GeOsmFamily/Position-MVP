import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  /* {
    path: "/",
    name: "Home",
    component: Home,
  },*/
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  // Dashboards

  {
    path: "/",
    name: "analytics",
    component: () => import("../DemoPages/Dashboards/Analytics.vue"),
  },

  // Pages

  {
    path: "/pages/login-boxed",
    name: "login-boxed",
    meta: { layout: "userpages" },
    component: () => import("../DemoPages/UserPages/LoginBoxed.vue"),
  },
  {
    path: "/category/create",
    name: "category-create",
    component: () => import("../DemoPages/Category/Layouts.vue"),
  },
  {
    path: "/category/list",
    name: "category-list",
    component: () => import("../DemoPages/Category/RegularTables.vue"),
  },
  {
    path: "/commercial/create",
    name: "commercial-create",
    component: () => import("../DemoPages/Commerciaux/Layouts.vue"),
  },
  {
    path: "/commercial/:id/edit",
    name: "commercial-edit",
    component: () => import("../DemoPages/Commerciaux/Edit.vue"),
  },
  {
    path: "/zone/create",
    name: "zone-create",
    component: () => import("../DemoPages/Zone/Layouts.vue"),
  },
  {
    path: "/commercial/list",
    name: "commercial-list",
    component: () => import("../DemoPages/Commerciaux/RegularTables.vue"),
  },
  {
    path: "/business/list",
    name: "business-list",
    component: () => import("../DemoPages/Business/RegularTables.vue"),
  },
  {
    path: "/commercial/:id",
    name: "commercial-details",
    component: () => import("../DemoPages/Commerciaux/Details.vue"),
  },
  {
    path: "/pages/register-boxed",
    name: "register-boxed",
    meta: { layout: "userpages" },
    component: () => import("../DemoPages/UserPages/RegisterBoxed.vue"),
  },
  {
    path: "/pages/forgot-password-boxed",
    name: "forgot-password-boxed",
    meta: { layout: "userpages" },
    component: () => import("../DemoPages/UserPages/ForgotPasswordBoxed.vue"),
  },

  // Elements

  {
    path: "/elements/buttons-standard",
    name: "buttons-standard",
    component: () => import("../DemoPages/Elements/Buttons/Standard.vue"),
  },
  {
    path: "/elements/dropdowns",
    name: "dropdowns",
    component: () => import("../DemoPages/Elements/Dropdowns.vue"),
  },
  {
    path: "/elements/icons",
    name: "icons",
    component: () => import("../DemoPages/Elements/Icons.vue"),
  },
  {
    path: "/elements/badges-labels",
    name: "badges",
    component: () => import("../DemoPages/Elements/Badges.vue"),
  },
  {
    path: "/elements/cards",
    name: "cards",
    component: () => import("../DemoPages/Elements/Cards.vue"),
  },
  {
    path: "/elements/list-group",
    name: "list-group",
    component: () => import("../DemoPages/Elements/ListGroups.vue"),
  },
  {
    path: "/elements/timelines",
    name: "timeline",
    component: () => import("../DemoPages/Elements/Timeline.vue"),
  },
  {
    path: "/elements/utilities",
    name: "utilities",
    component: () => import("../DemoPages/Elements/Utilities.vue"),
  },

  // Components

  {
    path: "/components/tabs",
    name: "tabs",
    component: () => import("../DemoPages/Components/Tabs.vue"),
  },
  {
    path: "/components/accordions",
    name: "accordions",
    component: () => import("../DemoPages/Components/Accordions.vue"),
  },
  {
    path: "/components/modals",
    name: "modals",
    component: () => import("../DemoPages/Components/Modals.vue"),
  },
  {
    path: "/components/progress-bar",
    name: "progress-bar",
    component: () => import("../DemoPages/Components/ProgressBar.vue"),
  },
  {
    path: "/components/tooltips-popovers",
    name: "tooltips-popovers",
    component: () => import("../DemoPages/Components/TooltipsPopovers.vue"),
  },
  {
    path: "/components/carousel",
    name: "carousel",
    component: () => import("../DemoPages/Components/Carousel.vue"),
  },
  {
    path: "/components/pagination",
    name: "pagination",
    component: () => import("../DemoPages/Components/Pagination.vue"),
  },
  {
    path: "/components/maps",
    name: "maps",
    component: () => import("../DemoPages/Components/Maps.vue"),
  },

  // Tables

  {
    path: "/tables/regular-tables",
    name: "regular-tables",
    component: () => import("../DemoPages/Tables/RegularTables.vue"),
  },

  // Dashboard Widgets

  {
    path: "/widgets/chart-boxes-3",
    name: "chart-boxes-3",
    component: () => import("../DemoPages/Widgets/ChartBoxes3.vue"),
  },

  // Forms

  {
    path: "/forms/controls",
    name: "controls",
    component: () => import("../DemoPages/Forms/Elements/Controls.vue"),
  },
  {
    path: "/forms/layouts",
    name: "layouts",
    component: () => import("../DemoPages/Forms/Elements/Layouts.vue"),
  },
  // Charts

  {
    path: "/charts/chartjs",
    name: "chartjs",
    component: () => import("../DemoPages/Charts/Chartjs.vue"),
  },
];

const router = new VueRouter({
  scrollBehavior() {
    return window.scrollTo({ top: 0, behavior: "smooth" });
  },
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
