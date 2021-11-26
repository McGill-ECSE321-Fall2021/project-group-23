<template>
  <div id="profile">
    <customer-profile
      username="habibi"
      password="lol123"
      id="12345"
      address="lol Street"
      email="habibi@lol.com"
      accountType="habibi type"
    />
    <p>
      <!-- use router-link component for navigation. -->
      <!-- specify the link by passing the `to` prop. -->
      <!-- `<router-link>` will be rendered as an `<a>` tag by default -->
      <router-link to="/test/Bashar">Go to Test with name Bashar</router-link>
    </p>
  </div>
</template>
<script>
import CustomerProfile from "@/components/CustomerProfile.vue";
export default {
  components: {
    CustomerProfile,
  },
};
</script>
<style>
</style>