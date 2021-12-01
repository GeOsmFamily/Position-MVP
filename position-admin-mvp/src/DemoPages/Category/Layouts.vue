<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
    ></page-title>
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h5 class="card-title">Nouvelle Catégorie</h5>
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
                placeholder="Nom de la catégorie"
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
                  width="20"
                  height="20"
                ></b-img>
                <!--                <img class="preview my-3" :src="previewImage" alt="" />-->
              </div>
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
import { required, requiredIf } from "vuelidate/lib/validators";
export default {
  components: {
    PageTitle,
  },
  data: () => ({
    heading: "Catégorie",
    subheading: "Ajouter une nouvelle catégorie",
    icon: "pe-7s-graph text-success",
    name: "",
    logo: null,
    previewImage: null,
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
      required: requiredIf(function () {
        return this.logo !== "";
      }),
    },
  },
  methods: {
    handleFileUpload(event) {
      console.log(event.target.files[0]);
      this.logo = event.target.files[0];
      this.previewImage = URL.createObjectURL(this.logo);
    },
    handleLogin() {
      this.$store.commit("category/toggleLoading", true);
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$store.commit("category/toggleLoading", false);
      } else {
        if (this.name) {
          console.log("requête envoyée");
          let formData = new FormData();
          formData.append("file", this.logo);
          formData.append("nom", this.name);
          this.$store.dispatch("category/createCategory", formData).then(
            (data) => {
              console.log(data);
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
