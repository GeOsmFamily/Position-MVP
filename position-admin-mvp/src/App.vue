<template>
  <div id="app">
    <component :is="layout">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </component>
  </div>
</template>

<script>
const default_layout = "default";
import "./service/api";
export default {
  computed: {
    layout() {
      return (this.$route.meta.layout || default_layout) + "-layout";
    },
  },
  beforeCreate() {
    this.$store
      .dispatch("user/getUser")
      .then((data) => {
        console.log(data);
      })
      .catch((onerror) => {
        console.log(onerror);
        this.$store.dispatch("auth/logout").then(() => {
          this.$router.push("/pages/login-boxed");
        });
      });
    this.$store
      .dispatch("zone/fetchZones")
      .then((data) => {
        console.log(data);
      })
      .catch((onerror) => {
        console.error(onerror);
      });
  },
};
</script>

<style lang="scss">
@import "assets/base.scss";
</style>
