<template>
  <div>
    <b-overlay
      :show="deleteLoading"
      :variant="variant"
      :opacity="0.85"
      :blur="blur"
      rounded="sm"
    >
      <page-title
        :heading="heading"
        :subheading="subheading"
        :icon="icon"
      ></page-title>
      <div class="text-center" v-if="loading">
        <b-spinner variant="success" label="Spinning"></b-spinner>
      </div>
      <b-card title="Commerciaux" class="main-card mb-4" v-if="!loading">
        <vue-good-table
          :columns="fields"
          :rows="commerciaux"
          :fixed-header="true"
          :search-options="{
            enabled: true,
            skipDiacritics: true,
            placeholder: 'Rechercher un commercial',
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
              <b-button
                class="mx-1"
                variant="info"
                @click="commercialDetails(props.row)"
                >Détails</b-button
              >
              <b-button
                variant="success"
                class="mx-1"
                @click="modify(props.row)"
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
          <div slot="emptystate">
            No data yet.<b-button @click="getCommerciaux()">Réessayer</b-button>
          </div>
        </vue-good-table>

        <b-modal
          id="my-modal"
          title="Supprimer le commercial"
          hide-backdrop
          hide-footer
        >
          <div class="d-block text-center">
            <h5>
              Voulez vous vraiment supprimer le commercial
              {{ currentRow != null ? currentRow.name : "" }}!
            </h5>
          </div>
          <b-button
            class="mt-3"
            variant="outline-danger"
            @click="deleteCommercial"
            block
            >Supprimer</b-button
          >
          <b-button
            class="mt-2"
            variant="outline-dark "
            @click="closeModal"
            block
            >Annuler</b-button
          >
        </b-modal>
      </b-card>
      <template #overlay>
        <div class="text-center">
          <b-icon icon="stopwatch" font-scale="5" animation="cylon"></b-icon>
          <p id="cancel-label">Patienter...</p>
          <b-button
            ref="cancel"
            variant="outline-danger"
            size="sm"
            aria-describedby="cancel-label"
            @click="show = false"
          >
            Cancel
          </b-button>
        </div>
      </template>
    </b-overlay>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle.vue";
import "vue-good-table/dist/vue-good-table.css";
import { required } from "vuelidate/lib/validators";

export default {
  components: {
    PageTitle,
  },
  data: () => ({
    name: "",
    logo: "",
    nameState: null,
    message: "",
    submittedNames: [],
    editLoading: false,
    submitted: false,
    heading: "Commerciaux",
    subheading: "Liste des commerciaux",
    icon: "pe-7s-drawer icon-gradient bg-happy-itmeo",
    currentRow: null,
    deleteLoading: false,
    variant: "dark",
    opacity: 0.85,
    blur: "2px",
    fields: [
      {
        label: "No",
        field: "id",
        type: "number",
      },
      {
        label: "No Badge",
        field: "numeroBadge",
        type: "number",
      },
      {
        label: "Nom",
        field: "name",
        type: "string",
      },
      {
        label: "Email",
        field: "email",
        type: "string",
      },
      {
        label: "Contact",
        field: "phone",
        type: "string",
      },
      {
        label: "Ville",
        field: "ville",
        type: "string",
      },
      /*{
        label: "Crée le",
        field: "created_at",
        type: "date",
        dateInputFormat: "dd/mm/yyyy",
        dateOutputFormat: "dd/mm/yyyy",
      },
      {
        label: "Mis à jour le",
        field: "updated_at",
        type: "date",
        dateInputFormat: "dd/mm/yyyy",
        dateOutputFormat: "dd/mm/yyyy",
      },*/
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
  validations: {
    name: {
      required,
    },
  },
  computed: {
    loading() {
      return this.$store.getters["commercial/loading"];
    },
    commerciaux() {
      return this.$store.getters["commercial/commerciaux"];
    },
  },
  created() {
    if (this.commerciaux == null || this.commerciaux.length === 0)
      this.getCommerciaux();
  },
  methods: {
    getCommerciaux() {
      this.$store.dispatch("commercial/fetchCommerciaux");
    },
    setRow(data) {
      this.currentRow = data;
      this.name = data.nom;
      this.logo = data.logo_url;
    },
    modify(commercial) {
      this.$store.dispatch("commercial/setCurrentCommercial", commercial);
      this.$router.push({
        path: `/commercial/${commercial.id}/edit`,
      });
    },
    closeModal() {
      this.$bvModal.hide("my-modal");
    },
    deleteCommercial() {
      this.$bvModal.hide("my-modal");
      this.deleteLoading = true;
      this.$store
        .dispatch("commercial/deleteCommercial", this.currentRow.id)
        .then((data) => {
          this.deleteLoading = false;
          console.log(data);
        })
        .catch((error) => {
          this.deleteLoading = false;
          console.log(error);
        });
    },
    commercialDetails(commercial) {
      this.$store.dispatch("commercial/setCurrentCommercial", commercial);
      this.$router.push({
        path: `/commercial/${commercial.id}`,
      });
    },
    editCategory() {
      console.log(this.logo);
      this.editLoading = true;
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.editLoading = false;
      } else {
        if (this.name) {
          this.$store
            .dispatch("category/editCategory", {
              id: this.currentRow.id,
              category: {
                nom: this.name,
              },
            })
            .then((result) => {
              this.editLoading = false;
              console.log(result);
              this.$bvToast.toast(
                `Modification de ${this.currentRow.nom} avec succès`,
                {
                  title: "Information",
                  variant: "success",
                  autoHideDelay: 5000,
                  appendToast: true,
                  solid: true,
                }
              );
            })
            .catch((error) => {
              console.log(error.data);
              this.editLoading = false;
              this.message =
                (error.data.errors && error.data.message) ||
                error.data.errors.toString() ||
                error.data.toString();
            });
        }
      }
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
