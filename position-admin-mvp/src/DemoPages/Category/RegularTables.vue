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
      <vue-good-table :columns="fields" :rows="categories" :fixed-header="true">
        <div slot="emptystate">No data yet.</div>
      </vue-good-table>
      <!--      <vuetable
        ref="vuetable"
        api-url="https://vuetable.ratiw.net/api/users"
        :fields="fields"
        :api-mode="false"
        :data="categories"
        :per-page="perPage"
        :data-manager="dataManager"
        pagination-path="pagination"
        @vuetable:pagination-data="onPaginationData"
      >
        <div slot="actions" slot-scope="props">
          <button
            class="ui small button"
            @click="onActionClicked('view-item', props.rowData)"
          >
            <i class="zoom icon"></i>
          </button>
          <button
            class="ui small button"
            @click="onActionClicked('edit-item', props.rowData)"
          >
            <i class="edit icon"></i>
          </button>
          <button
            class="ui small button"
            @click="onActionClicked('delete-item', props.rowData)"
          >
            <i class="delete icon"></i>
          </button>
        </div>
      </vuetable>-->
      <!--      <b-table
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
      </b-table>-->
    </b-card>
    <!--    <div style="padding-top: 10px">
      <vuetable-pagination
        ref="pagination"
        @vuetable-pagination:change-page="onChangePage"
      ></vuetable-pagination>
    </div>-->
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle.vue";
// import Vuetable from "vuetable-2";
// import VuetablePagination from "vuetable-2/src/components/VuetablePagination";
import _ from "lodash";
import "vue-good-table/dist/vue-good-table.css";
export default {
  components: {
    PageTitle,
    // Vuetable,
    // VuetablePagination,
  },
  data: () => ({
    heading: "Catégories",
    subheading: "Liste des catégories",
    icon: "pe-7s-drawer icon-gradient bg-happy-itmeo",

    fields: [
      {
        label: "No",
        field: "id",
        type: "number",
      },
      {
        label: "Nom",
        field: "nom",
        type: "string",
      },
      {
        label: "Crée le",
        field: "created_at",
        type: "date",
        dateInputFormat: "yyyy-MM-dd",
        dateOutputFormat: "MMM do yy",
      },
      {
        label: "Mis à jour le",
        field: "updated_at",
        type: "date",
        dateInputFormat: "yyyy-MM-dd",
        dateOutputFormat: "MMM do yy",
      },
    ],
    perPage: 10,
    striped: false,
    bordered: false,
    outlined: false,
    small: false,
    hover: false,
    dark: false,
    fixed: false,
    footClone: false,
  }),
  watch: {
    data(newVal, oldVal) {
      console.log(newVal, oldVal);
      this.$refs.vuetable.refresh();
    },
  },
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
  methods: {
    onPaginationData(paginationData) {
      this.$refs.pagination.setPaginationData(paginationData);
    },
    onChangePage(page) {
      this.$refs.vuetable.changePage(page);
    },
    dataManager(sortOrder, pagination) {
      if (this.data.length < 1) return;

      let local = this.data;

      // sortOrder can be empty, so we have to check for that as well
      if (sortOrder.length > 0) {
        console.log("orderBy:", sortOrder[0].sortField, sortOrder[0].direction);
        local = _.orderBy(
          local,
          sortOrder[0].sortField,
          sortOrder[0].direction
        );
      }

      pagination = this.$refs.vuetable.makePagination(
        local.length,
        this.perPage
      );
      console.log("pagination:", pagination);
      let from = pagination.from - 1;
      let to = from + this.perPage;

      return {
        pagination: pagination,
        data: _.slice(local, from, to),
      };
    },
    onActionClicked(action, data) {
      console.log("slot actions: on-click", data.name);
    },
  },
};
</script>
<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 20px;
}
button.ui.button {
  padding: 8px 3px 8px 10px;
  margin-top: 1px;
  margin-bottom: 1px;
}
</style>
