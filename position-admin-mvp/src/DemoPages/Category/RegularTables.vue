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
      <vue-good-table
        :columns="fields"
        :rows="categories"
        :fixed-header="true"
        :search-options="{
          enabled: true,
          skipDiacritics: true,
          placeholder: 'Rechercher une catégorie',
        }"
        :pagination-options="{
          enabled: true,
          mode: 'pages',
          perPage: 5,
          position: 'bottom',
          perPageDropdown: [10, 15],
          dropdownAllowAll: false,
          jumpFirstOrLast: true,
          firstLabel: 'Début',
          lastLabel: 'Fin',
          nextLabel: 'suiv',
          prevLabel: 'prec',
          rowsPerPageLabel: 'Lignes par page',
          ofLabel: 'de',
          pageLabel: 'page', // for 'pages' mode
          allLabel: 'Tout',
        }"
        styleClass="vgt-table bordered striped"
        compactMode
      >
        <template slot="table-row" slot-scope="props">
          <span v-if="props.column.field === 'actions'">
            <b-button class="mx-1" variant="info" @click="editRow(props.row.id)"
              >Détails</b-button
            >
            <b-button
              variant="success"
              class="mx-1"
              @click="editRow(props.row.id)"
              >Modifier</b-button
            >
            <b-button
              variant="danger"
              class="mx-1"
              v-b-modal.my-modal
              @click="setRow(props.row)"
              >Supprimer</b-button
            >
          </span>
          <span v-else>
            {{ props.formattedRow[props.column.field] }}
          </span>
        </template>
        <div slot="emptystate">No data yet.</div>
      </vue-good-table>

      <b-modal
        id="my-modal"
        title="Supprimer la catégorie"
        hide-backdrop
        hide-footer
      >
        <div class="d-block text-center">
          <h5>
            Voulez vous vraiment supprimer la catégorie
            {{ currentRow != null ? currentRow.id : "" }}!
          </h5>
        </div>
        <b-button class="mt-3" variant="outline-danger" block
          >Close Me</b-button
        >
        <b-button class="mt-2" variant="outline-warning" block
          >Toggle Me</b-button
        >
      </b-modal>
    </b-card>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle.vue";
import "vue-good-table/dist/vue-good-table.css";
export default {
  components: {
    PageTitle,
  },
  data: () => ({
    heading: "Catégories",
    subheading: "Liste des catégories",
    icon: "pe-7s-drawer icon-gradient bg-happy-itmeo",
    currentRow: null,
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
      {
        label: "Actions",
        field: "actions",
        sortable: false,
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
    setRow(data) {
      this.currentRow = data;
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
