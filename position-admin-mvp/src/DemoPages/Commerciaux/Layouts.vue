<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
    ></page-title>
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h5 class="card-title">Nouveau Commercial</h5>
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
                placeholder="Nom du commercial"
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
              <label for="Email22" class="mr-sm-2">Email</label
              ><b-form-input
                name="email"
                v-model="email"
                v-model.trim="$v.email.$model"
                :state="!submitted ? null : submitted && !$v.email.$invalid"
                id="Email22"
                placeholder="Email du commercial"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.email.required"
              >
                Field is required
              </b-form-invalid-feedback>
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.email.email"
              >
                This field must be an email
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Badge22" class="mr-sm-2">Numéro Badge</label
              ><b-form-input
                name="badge"
                v-model="badge"
                v-model.trim="$v.badge.$model"
                :state="!submitted ? null : submitted && !$v.badge.$invalid"
                id="Badge22"
                placeholder="Numéro de badge du commercial"
                type="number"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.badge.required"
              >
                Field is required
              </b-form-invalid-feedback>
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.badge.numeric"
              >
                This field must be a number
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Phone22" class="mr-sm-2">Telephone</label>
              <vue-tel-input v-model="phone"></vue-tel-input>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="town22" class="mr-sm-2">Ville</label
              ><b-form-input
                name="logo"
                v-model="town"
                v-model.trim="$v.town.$model"
                :state="!submitted ? null : submitted && !$v.town.$invalid"
                id="town22"
                placeholder="Ville du commercial"
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
              <label for="zone22" class="mr-sm-2">Zone</label
              ><b-form-input
                name="logo"
                v-model="zone"
                v-model.trim="$v.zone.$model"
                :state="!submitted ? null : submitted && !$v.zone.$invalid"
                id="zone22"
                placeholder="Ville du commercial"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.zone.required"
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label class="mr-sm-2">Photo Commercial</label>
              <b-form-file
                v-model="imageProfil"
                :state="submitted ? Boolean(imageProfil) : null"
                placeholder="Choisissez une photo de profil..."
                drop-placeholder="Drop file here..."
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
import { required, email, numeric } from "vuelidate/lib/validators";
export default {
  components: {
    PageTitle,
  },
  data: () => ({
    heading: "Commerciaux",
    subheading: "Ajouter une nouveau commercial",
    icon: "pe-7s-graph text-success",
    name: "",
    email: "",
    phone: "",
    badge: "",
    imageProfil: null,
    zone: "",
    town: "",
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
    email: {
      required,
      email,
    },
    town: {
      required,
    },
    zone: {
      required,
    },
    badge: {
      required,
      numeric,
    },
  },
  methods: {
    handleLogin() {
      this.$store.commit("category/toggleLoading", true);
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$store.commit("category/toggleLoading", false);
      } else {
        if (this.name) {
          console.log("requête envoyée");
          this.$store
            .dispatch("category/createCategory", {
              nom: this.name,
              logo_url: this.logo,
            })
            .then(
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
