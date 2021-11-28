<template>
  <div id="profile">
    <customer-profile
      usernameP="habibi"
      passwordP="lol123"
      idP="12345"
      addressP="lol Street"
      emailP="habibi@lol.com"
      accountTypeP="habibi type"
    />
    <p>
      <button v-on:click="go">click to route </button>
      <!-- use router-link component for navigation. -->
      <!-- specify the link by passing the `to` prop. -->
      <!-- `<router-link>` will be rendered as an `<a>` tag by default -->
      <!--<router-link to="/test/Bashar">Go to Test with name Bashar</router-link>-->
    </p>
  </div>
</template>
<script>
import CustomerProfile from "@/components/CustomerProfile.vue";
export default {
  components: {
    CustomerProfile,
  },
  methods: {

    go: function(){
      var path = "bashar"
      this.$router.push({ path: `/test/${path}` })
    }
  }
};
</script>
<style>
</style>