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
              <b-button
                class="mx-1"
                variant="info"
                @click="editRow(props.row.id)"
                >Détails</b-button
              >
              <b-button
                variant="success"
                class="mx-1"
                v-b-modal.edit-modal
                @click="setRow(props.row)"
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
            <span v-if="props.column.field === 'logoUrl'">
              <center>
                <b-img
                  :src="
                    'https://services.position.cm' +
                    props.formattedRow[props.column.field]
                  "
                  height="20"
                  width="20"
                  alt="Logo catégorie"
                  v-if="
                    props.formattedRow[props.column.field] !== '' ||
                    props.formattedRow[props.column.field] != null
                  "
                ></b-img>
              </center>
            </span>
            <span v-else>
              {{ props.formattedRow[props.column.field] }}
            </span>
          </template>
          <div slot="emptystate">
            No data yet.<b-button @click="getCategories">Réessayer</b-button>
          </div>
        </vue-good-table>

        <div>
          <b-modal
            id="edit-modal"
            ref="modal"
            title="Modifier la catégorie"
            hide-backdrop
            hide-footer
          >
            <b-form name="category">
              <b-form-group
                id="input-group-1"
                label="Nom catégorie:"
                label-for="input-1"
                description=""
                ><b-form-input
                  name="name"
                  v-model="name"
                  v-model.trim="$v.name.$model"
                  :state="!submitted ? null : submitted && !$v.name.$invalid"
                  id="Name22"
                  placeholder="Nom de la catégorie"
                  type="text"
                  :value="currentRow != null ? currentRow.nom : 'test'"
                  class="form-control"
                />
                <b-form-invalid-feedback
                  :state="!submitted ? null : submitted && $v.name.required"
                >
                  Field is required
                </b-form-invalid-feedback>
              </b-form-group>
              <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
                <label class="mr-sm-2">Logo catégorie</label>
                <b-form-file
                  v-model="logo"
                  :state="submitted ? Boolean(logo) : null"
                  placeholder="Choisissez un logo pour la catégorie..."
                  drop-placeholder="Drop file here..."
                  @change="handleFileUpload($event)"
                ></b-form-file>
              </div>
              <br />
              <div v-if="previewImage">
                <div>
                  <b-img
                    :src="previewImage"
                    fluid
                    alt="Fluid image"
                    width="400"
                    height="400"
                    v-if="previewImage != null"
                  ></b-img>
                  <b-img
                    :src="'https://services.position.cm' + currentRow.logoUrl"
                    fluid
                    alt="Fluid image"
                    width="400"
                    height="400"
                    v-if="previewImage == null"
                  ></b-img>
                </div>
              </div>
              <div class="form-group">
                <div v-if="message" class="alert alert-danger" role="alert">
                  {{ message }}
                </div>
              </div>
              <br />
              <div class="float-right">
                <b-button
                  variant="success"
                  :disabled="editLoading"
                  @click="editCategory"
                >
                  <span
                    v-show="editLoading"
                    class="spinner-border spinner-border-sm"
                  ></span>
                  <span>Modifier</span>
                </b-button>
              </div>
            </b-form>
          </b-modal>
        </div>
        <b-modal
          id="my-modal"
          title="Supprimer la catégorie"
          hide-backdrop
          hide-footer
        >
          <div class="d-block text-center">
            <h5>
              Voulez vous vraiment supprimer la catégorie
              {{ currentRow != null ? currentRow.nom : "" }}!
            </h5>
          </div>
          <b-button
            class="mt-3"
            variant="outline-danger"
            @click="deleteCategory"
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
    <b-toast id="example-toast" title="BootstrapVue" static no-auto-hide>
      Hello, world! This is a toast message.
    </b-toast>
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
    logo: null,
    previewImage: null,
    nameState: null,
    message: "",
    submittedNames: [],
    editLoading: false,
    submitted: false,
    heading: "Catégories",
    subheading: "Liste des catégories",
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
        label: "Logo",
        field: "logoUrl",
        type: "string",
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
        dateInputFormat: "dd/mm/yyyy",
        dateOutputFormat: "dd/mm/yyyy",
      },
      {
        label: "Mis à jour le",
        field: "updated_at",
        type: "date",
        dateInputFormat: "dd/mm/yyyy",
        dateOutputFormat: "dd/mm/yyyy",
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
  validations: {
    name: {
      required,
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
      this.getCategories();
  },
  methods: {
    getCategories() {
      this.$store.dispatch("category/fetchCategories");
    },
    handleFileUpload(event) {
      console.log(event.target.files[0]);
      this.logo = event.target.files[0];
      this.previewImage = URL.createObjectURL(this.logo);
    },
    setRow(data) {
      console.log(data);
      this.currentRow = data;
      this.name = data.nom;
    },
    closeModal() {
      this.$bvModal.hide("my-modal");
    },
    deleteCategory() {
      this.$bvModal.hide("my-modal");
      this.deleteLoading = true;
      this.$store
        .dispatch("category/deleteCategory", this.currentRow.id)
        .then((data) => {
          this.deleteLoading = false;
          console.log(data);
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
          this.deleteLoading = false;
          console.log(error);
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
          let formData = new FormData();
          console.log(this.name);
          if (this.logo !== null) formData.append("file", this.logo);
          formData.append("nom", this.name);
          formData.append("_method", "put");
          this.$store
            .dispatch("category/editCategory", {
              id: this.currentRow.id,
              category: formData,
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
