<template>
  <div id="profile">
    <customer-profile
      nameP= "bashar"
      familynameP="eskandar"
      passwordP="Lollollol"
      idP="1"
      addressP="611 rue lol"
      emailP="bashar@hotmail.com"
      accountTypeP="Customer"
    />
    
      <!--<button v-on:click="go">click to route </button>-->
      <!-- use router-link component for navigation. -->
      <!-- specify the link by passing the `to` prop. -->
      <!-- `<router-link>` will be rendered as an `<a>` tag by default -->
      <!--<router-link to="/test/Bashar">Go to Test with name Bashar</router-link>-->
    
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
      var a = "bashar"
      this.$router.push({ path: `/customerhomepage/${a}/dfgfdg/fgf/` })
    }
  }
};
</script>
<style>
</style>