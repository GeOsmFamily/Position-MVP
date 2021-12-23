<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
    ></page-title>
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h5 class="card-title">Modifier Commercial</h5>
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
                :value="'Ivan'"
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
                :value="commercial.email"
                placeholder="Email du commercial"
                type="email"
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
            <b-form-group label="Sexe" v-slot="{ ariaDescribedby }">
              <b-form-radio-group
                id="radio-slots"
                v-model="sexe"
                :options="options"
                :aria-describedby="ariaDescribedby"
                name="radio-options-slots"
              >
              </b-form-radio-group>
            </b-form-group>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Age22" class="mr-sm-2">Age</label
              ><b-form-input
                name="badge"
                v-model="age"
                v-model.trim="$v.age.$model"
                :state="!submitted ? null : submitted && !$v.age.$invalid"
                id="Age22"
                placeholder="Age du commercial"
                type="number"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.age.required"
              >
                Field is required
              </b-form-invalid-feedback>
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.age.numeric"
              >
                This field must be a number
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
              <label for="Badge22" class="mr-sm-2">Numéro CNI</label
              ><b-form-input
                name="badge"
                v-model="numeroCni"
                v-model.trim="$v.numeroCni.$model"
                :state="!submitted ? null : submitted && !$v.numeroCni.$invalid"
                id="Badge22"
                placeholder="Numéro de CNI du commercial"
                type="number"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.numeroCni.required"
              >
                Field is required
              </b-form-invalid-feedback>
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.numeroCni.numeric"
              >
                This field must be a number
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Phone22" class="mr-sm-2">Telephone</label>
              <vue-tel-input
                v-model="phone"
                v-model.trim="$v.phone.$model"
                :state="!submitted ? null : submitted && !$v.phone.$invalid"
                id="Phone22"
              ></vue-tel-input>
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.phone.required"
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="town22" class="mr-sm-2">Diplome</label
              ><b-form-input
                name="diplome"
                v-model="diplome"
                v-model.trim="$v.diplome.$model"
                :state="!submitted ? null : submitted && !$v.diplome.$invalid"
                id="town22"
                placeholder="Diplome du commercial"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.diplome.required"
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <b-form-group
              label="Taille T-shirt"
              v-slot="{ ariaDescribedbySizes }"
            >
              <b-form-radio-group
                id="taille-slots"
                v-model="tailleTshirt"
                :options="sizes"
                :aria-describedby="ariaDescribedbySizes"
                name="taille-options-slots"
              >
              </b-form-radio-group>
            </b-form-group>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="town22" class="mr-sm-2">Ville</label
              ><b-form-input
                name="town"
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
              ><b-form-select
                name="zone"
                v-model="selectedZone"
                :options="zones === null ? [] : zones"
                v-model.trim="$v.selectedZone.$model"
                :state="
                  !submitted ? null : submitted && !$v.selectedZone.$invalid
                "
                id="zone22"
                placeholder="Zone du commercial"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="
                  !submitted ? null : submitted && $v.selectedZone.required
                "
              >
                Field is required
              </b-form-invalid-feedback>
            </div>
            <br />
            <div class="mb-2 mr-sm-2 mb-sm-0 position-relative form-group">
              <label for="Quartier22" class="mr-sm-2">Quartier</label
              ><b-form-input
                name="quartier"
                v-model="quartier"
                v-model.trim="$v.quartier.$model"
                :state="!submitted ? null : submitted && !$v.quartier.$invalid"
                id="zone22"
                placeholder="Quartier du commercial"
                type="text"
                class="form-control"
              />
              <b-form-invalid-feedback
                :state="!submitted ? null : submitted && $v.quartier.required"
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
                ></b-img>
                <!--                <img class="preview my-3" :src="previewImage" alt="" />-->
              </div>
            </div>
            <br />
            <div class="float-right">
              <b-button
                :disabled="loading"
                @click="createCommercial"
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
    subheading: "Modifier un commercial",
    icon: "pe-7s-graph text-success",
    name: "",
    email: "",
    diplome: "",
    phone: "",
    options: [
      { text: "Masculin", value: "M" },
      { text: "Feminin", value: "F" },
    ],
    sizes: [
      { text: "S", value: "S" },
      { text: "M", value: "M" },
      { text: "L", value: "L" },
      { text: "XL", value: "XL" },
    ],
    badge: "",
    quartier: "",
    numeroCni: "",
    previewImage: null,
    imageProfil: null,
    selectedZone: "",
    age: "",
    sexe: "",
    tailleTshirt: "",
    town: "",
    message: "",
    submitted: false,
  }),
  computed: {
    loading() {
      return this.$store.getters["commercial/loading"];
    },
    zones() {
      return this.$store.getters["zone/zonesOPtions"];
    },
    commercial() {
      return this.$store.getters["commercial/commercialCourant"];
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
    phone: {
      required,
    },
    town: {
      required,
    },
    selectedZone: {
      required,
    },
    badge: {
      required,
      numeric,
    },
    numeroCni: {
      required,
      numeric,
    },
    diplome: {
      required,
    },
    tailleTshirt: {
      required,
    },
    age: {
      required,
      numeric,
    },
    quartier: {
      required,
    },
  },
  created() {
    console.log(this.$store.getters["commercial/commercialCourant"]);
    this.$toast.open({
      message: "Something went wrong!",
      type: "error",
      position: "top-right",
      duration: 10000,
    });
  },
  mounted() {
    console.log(this.$store.getters["commercial/commercialCourant"]);
    this.$toast.open({
      message: "Something went wrong!",
      type: "error",
      position: "top-right",
      duration: 10000,
    });
  },

  methods: {
    handleFileUpload(event) {
      console.log(event.target.files[0]);
      this.imageProfil = event.target.files[0];
      this.previewImage = URL.createObjectURL(this.imageProfil);
    },
    createCommercial() {
      this.$store.commit("commercial/toggleLoading", true);
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$store.commit("commercial/toggleLoading", false);
      } else {
        if (this.name) {
          let formData = new FormData();
          formData.append("file", this.imageProfil);
          formData.append("name", this.name);
          formData.append("email", this.email);
          formData.append("whatsapp", this.phone);
          formData.append("phone", this.phone);
          formData.append("ville", this.town);
          formData.append("idZone", 1);
          formData.append("numeroCni", this.numeroCni);
          formData.append("numeroBadge", this.badge);
          formData.append("quartier", this.quartier);
          formData.append("sexe", this.sexe);
          formData.append("age", this.age);
          formData.append("diplome", this.diplome);
          formData.append("tailleTshirt", this.tailleTshirt);

          this.$store.dispatch("commercial/createCommercial", formData).then(
            (data) => {
              console.log(data);
              this.$toast.open("You did it!");
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
