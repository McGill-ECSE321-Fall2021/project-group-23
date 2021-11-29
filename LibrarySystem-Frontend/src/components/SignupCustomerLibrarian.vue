<template>
<div id="SignupCustomerLibrarian">
    <table>
      <h2>Sign Up a Customer</h2>
      <!--
    These are the require input fields to create a new Customer: firstName, lastName, email, address and password
    -->
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
    <!--
    Creates a button that would call the signupCustomer function when all the fields above are not empty
    -->
      <tr>
          <td>
              <button v-bind:disabled="!firstName || !lastName || !address || !email || !password" @click="SignupCustomerLibrarian(firstName, lastName, address, email, password)">Sign Up</button>
          </td>
      </tr>  
    </table>
    <!--
    These three spans are used to display an error message when the the signupCustomer function is not successful
    -->
    <p>
        <span v-if="errorSignupCustomer" style="color:red">Error: {{errorSignupCustomer}}</span>
    </p>
    <p>
        <span v-if="errorSignupCustomer2" style="color:red">{{errorSignupCustomer2}}</span>
    </p>
    <p>
        <span v-if="errorSignupCustomer3" style="color:red">{{errorSignupCustomer3}}</span>
    </p>
  </div>
</template>
<script src="./SignupCustomerLibrarian.js">
</script>
<style>
</style>