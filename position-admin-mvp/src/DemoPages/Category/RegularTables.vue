<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
    ></page-title>
    <div class="text-center" v-if="loading">
      <b-spinner variant="success" label="Spinning"></b-spinner>
    </div>
    <b-card title="Catégories" class="main-card mb-4" v-if="!loading">
      <b-table
        :striped="true"
        :bordered="true"
        :outlined="true"
        :small="false"
        :hover="true"
        :dark="false"
        :fixed="true"
        :foot-clone="false"
        :items="categories"
        :fields="fields"
      >
      </b-table>
    </b-card>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle.vue";

export default {
  components: {
    PageTitle,
  },
  data: () => ({
    heading: "Catégories",
    subheading: "Liste des catégories",
    icon: "pe-7s-drawer icon-gradient bg-happy-itmeo",

    fields: ["id", "nom", "created_at", "updated_at", "logo_url"],
    items: [
      { age: 40, first_name: "Dickerson", last_name: "Macdonald" },
      { age: 21, first_name: "Larsen", last_name: "Shaw" },
      { age: 89, first_name: "Geneva", last_name: "Wilson" },
    ],
    striped: false,
    bordered: false,
    outlined: false,
    small: false,
    hover: false,
    dark: false,
    fixed: false,
    footClone: false,
  }),
  computed: {
    loading() {
      return this.$store.getters["category/loading"];
    },
    categories() {
      return this.$store.getters["category/categories"];
    },
  },
  created() {
    if (this.categories == null || this.categories.length === 0)
      this.$store.dispatch("category/fetchCategories");
  },
  methods: {},
};
</script>
