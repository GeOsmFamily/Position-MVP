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

export default {
  computed: {
    layout() {
      return (this.$route.meta.layout || default_layout) + "-layout";
    },
  },
  created(){
    this.$store.dispatch('user/getUser').then(
      () => {
        console.log("success");
      },
      error => {
        /*this.loading = false;
        this.message =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();*/
        console.log(error.message ||
          error.toString());
      }
    );
  }
};
</script>

<style lang="scss">
@import "assets/base.scss";
</style>
