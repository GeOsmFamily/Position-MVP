<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
    ></page-title>
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h5 class="card-title">Nouvelle Zone</h5>
        <div>
          <b-form name="category">
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Name22" class="mr-sm-2">Nom</label
              ><b-form-input
                name="name"
                v-model="name"
                v-model.trim="$v.name.$model"
                :state="!submitted ? null : submitted && !$v.name.$invalid"
                id="Name22"
                placeholder="Nom de la zone"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.name.required"
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Town22" class="mr-sm-2">Ville</label
              ><b-form-input
                name="name"
                v-model="town"
                v-model.trim="$v.town.$model"
                :state="!submitted ? null : submitted && !$v.town.$invalid"
                id="Town22"
                placeholder="Nom de la ville"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.town.required"
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label class="mr-sm-2">Limites</label>
              <b-form-file
                v-model="logo"
                :state="submitted ? Boolean(logo) : null"
                placeholder="Choisissez un fichier de limite pour la zone..."
                drop-placeholder="Drop file here..."
                @change="handleFileUpload($event)"
              ></b-form-file>
            </div>
            <br />
            <div class="float-right">
              <b-button
                :disabled="loading"
                @click="handleLogin"
                variant="success"
              >
                <span
                  v-show="loading"
                  class="spinner-border spinner-border-sm"
                ></span>
                <span>Enregistrer</span>
              </b-button>
            </div>
          </b-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle.vue";
import { required } from "vuelidate/lib/validators";
export default {
  components: {
    PageTitle,
  },
  data: () => ({
    heading: "Zone",
    subheading: "Ajouter une nouvelle zone",
    icon: "pe-7s-graph text-success",
    name: "",
    town: "",
    logo: null,
    message: "",
    submitted: false,
  }),
  computed: {
    loading() {
      return this.$store.getters["category/loading"];
    },
  },
  validations: {
    name: {
      required,
    },
    logo: {
      required,
    },
    town: {
      required,
    },
  },
  methods: {
    handleFileUpload(event) {
      console.log(event.target.files[0]);
      this.logo = event.target.files[0];
    },
    handleLogin() {
      this.$store.commit("zone/toggleLoading", true);
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$store.commit("zone/toggleLoading", false);
      } else {
        if (this.name) {
          console.log("requête envoyée");
          let formData = new FormData();
          formData.append("file", this.logo);
          formData.append("nom", this.name);
          formData.append("ville", this.town);
          this.$store.dispatch("zone/createZone", formData).then(
            (data) => {
              console.log(data);
              this.$bvToast.toast(`Modification  succès`, {
                title: "Information",
                variant: "success",
                autoHideDelay: 5000,
                noAutoHide: true,
                appendToast: true,
                solid: true,
              });
            },
            (error) => {
              console.log(error.response.data.detail[0]);
              this.message =
                (error.response && error.response.data) ||
                error.response.data.detail ||
                error.toString();
            }
          );
        }
      }
    },
  },
};
</script>
