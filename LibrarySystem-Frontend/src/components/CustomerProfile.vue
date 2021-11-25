<template>
  <div id="customer-profile">
      <div id="header">
        <h2>User Profile - {{accountType}}</h2>
        <h2>{{username}}</h2>
        <hr>
      </div>
      <div id="body">
          <h4>Account ID : {{id}}</h4>
          <h4>Email      : {{email}}</h4>
          <h4>Address    : {{address}}</h4>
          <h4>Password   : {{password}}</h4>
      </div>
  </div>
</template>
<script>
//import toadd from './toadd.vue'
/*export default{
    components: {
        toadd
    }
}*/
export default {
  props: ["username", "id", "address", "email", "password", "accountType"],
};
</script>
<style>
</style>