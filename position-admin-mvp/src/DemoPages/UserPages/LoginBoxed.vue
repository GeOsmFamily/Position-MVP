<template>
  <div>
    <div class="h-100 bg-plum-plate bg-animation">
      <div class="d-flex h-100 justify-content-center align-items-center">
        <b-col md="8" class="mx-auto app-login-box">
          <div class="app-logo-inverse mx-auto mb-3" />

          <div class="modal-dialog w-100 mx-auto">
            <div class="modal-content">
              <div class="modal-body">
                <div class="h5 modal-title text-center">
                  <h4 class="mt-2">
                    <div>Welcome back,</div>
                    <span>Please sign in to your account below.</span>
                  </h4>
                </div>
                <b-form name="form" @submit.stop.prevent>
                  <b-form-group
                    id="exampleInputGroup1"
                    label-for="exampleInput1"
                  >
                    <b-form-input
                      v-model="email"
                      :state="
                        !submitted ? null : submitted && !$v.email.$invalid
                      "
                      v-model.trim="$v.email.$model"
                      class="form-control"
                      name="email"
                      id="exampleInput1"
                      type="email"
                      placeholder="Enter email..."
                    >
                    </b-form-input>
                    <b-form-invalid-feedback
                      :state="
                        !submitted ? null : submitted && $v.email.required
                      "
                    >
                      Field is required
                    </b-form-invalid-feedback>
                    <b-form-invalid-feedback
                      :state="!submitted ? null : submitted && $v.email.email"
                    >
                      This field must be an email
                    </b-form-invalid-feedback>
                  </b-form-group>
                  <b-form-group
                    id="exampleInputGroup2"
                    label-for="exampleInput2"
                  >
                    <b-form-input
                      v-model="$v.password.$model"
                      :state="
                        !submitted ? null : submitted && !$v.password.$invalid
                      "
                      class="form-control"
                      name="password"
                      id="exampleInput2"
                      type="password"
                      required
                      placeholder="Enter password..."
                    >
                    </b-form-input>
                    <b-form-invalid-feedback
                      :state="
                        !submitted ? null : submitted && $v.password.required
                      "
                    >
                      Field is required
                    </b-form-invalid-feedback>
                    <b-form-invalid-feedback
                      :state="
                        !submitted ? null : submitted && $v.password.minLength
                      "
                    >
                      Password must have at least
                      {{ $v.password.$params.minLength.min }} letters.
                    </b-form-invalid-feedback>
                  </b-form-group>
                  <b-form-checkbox name="check" id="exampleCheck">
                    Keep me logged in
                  </b-form-checkbox>
                  <div class="form-group">
                    <div v-if="message" class="alert alert-danger" role="alert">
                      {{ message.message }}
                    </div>
                  </div>
                </b-form>
                <div class="divider" />
                <h6 class="mb-0">
                  No account?
                  <a href="javascript:void(0);" class="text-primary"
                    >Sign up now</a
                  >
                </h6>
              </div>
              <div class="modal-footer clearfix">
                <div class="float-left">
                  <a href="javascript:void(0);" class="btn-lg btn btn-link"
                    >Recover Password</a
                  >
                </div>
                <div class="float-right">
                  <b-button
                    variant="primary"
                    size="lg"
                    :disabled="loading"
                    @click="handleLogin"
                  >
                    <span
                      v-show="loading"
                      class="spinner-border spinner-border-sm"
                    ></span>
                    <span>Login to Dashboard</span>
                  </b-button>
                </div>
              </div>
            </div>
          </div>
          <div class="text-center text-white opacity-8 mt-3">
            Copyright &copy; ArchitectUI 2019
          </div>
        </b-col>
      </div>
    </div>
  </div>
</template>

<script>
import User from "../../models/user";
import { required, minLength, email } from "vuelidate/lib/validators";

export default {
  name: "Login",
  data() {
    return {
      user: new User("", ""),
      loading: false,
      submitted: false,
      message: "",
      email: "",
      password: "",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/");
    }
  },
  validations: {
    email: {
      required,
      email,
      minLength: minLength(4),
    },
    password: {
      required,
      minLength: minLength(4),
    },
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.loading = false;
      } else {
        const user = new User(this.email, this.password);
        if (user.email && user.password) {
          this.$store.dispatch("auth/login", user).then(
            () => {
              this.$router.push("/");
            },
            (error) => {
              this.loading = false;
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
};
</script>
