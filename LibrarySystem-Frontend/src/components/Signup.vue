<template>
<div id="Signup">
    <table>
      <h2>Sign Up</h2>
      <tr>
          <td>
              <input type="text" v-model="firstName" placeholder="First name">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="lastName" placeholder="Last name">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="address" placeholder="Address">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="email" placeholder="Email">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="password" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button v-bind:disabled="!firstName || !lastName || !address || !email || !password" @click="signupCustomer(firstName, lastName, address, email, password)">Sign Up</button>
          </td>
      </tr>  
    </table>
    <p>
        <span v-if="errorSignupCustomer" style="color:red">Error: {{errorSignupCustomer}}</span>
    </p>
  </div>
</template>
<script src="./signup.js">
</script>
<style>
</style>