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
          <b-form name="category" class="form-inline">
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
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="logo22" class="mr-sm-2">Logo</label
              ><b-form-input
                name="logo"
                v-model="logo"
                v-model.trim="$v.logo.$model"
                :state="!submitted ? null : submitted && !$v.logo.$invalid"
                id="logo22"
                placeholder="https://via.placeholder.com/150.png/09f/fff"
                type="text"
                class="form-control"
              />
            </div>
            <div class="float-right">
              <b-button :disabled="loading" @click="handleLogin">
                <span
                  v-show="loading"
                  class="spinner-border spinner-border-sm"
                ></span>
                <span>Submit</span>
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
    logo: "",
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
              name: this.name,
              logo: this.logo,
            })
            .then(
              (data) => {
                console.log(data);
                //this.$router.push("/");
              },
              (error) => {
                this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString();
              }
            );
        }
      }
    },
  },
  created() {
    //this.$store.dispatch("category/fetchCategories");
  },
};
</script>
