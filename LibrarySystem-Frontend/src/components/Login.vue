<template>
  <div id="Login">
  <div class="d-flex align-items-center justify-content-center"
  style="height: 200px;" id="LoginCustomer">
    <table>
      <h2>Customer Login</h2>
      <tr>
          <td>
              <input style="margin-top: 6px;" type="text" v-model="customerAccountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input style="margin-top: 6px;" type="text" v-model="customerPassword" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button style="margin-top: 8px;" class="btn btn-light" v-bind:disabled="!customerAccountId || !customerPassword"  @click="loginCustomer(customerAccountId, customerPassword)">Login</button>
          </td>
      </tr>
      <tr>
          <td>
              <button style="margin-top: 8px;" class="btn btn-light" v-on:click="signUpCustomer()">Create new Account</button>
          </td>
      </tr>  
    </table>
    <p>
        <span v-if="errorLoginCustomer" style="color:red">Error: {{errorLoginCustomer}}</span>
    </p>
  </div>
  <hr>
  <div class="d-flex align-items-center justify-content-center"
  style="height: 250px;" id="loginLibrarian">
  <table>
      <h2>Librarian Login</h2>
      <tr>
          <td>
              <input style="margin-top: 6px;" type="text" v-model="librarianAccountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input style="margin-top: 6px;" type="text" v-model="librarianPassword" placeholder="Password">
          </td>
      </tr>
      <!--
    These three spans are used to display an error message when the the signupCustomer function is not successful
    -->
      <tr>
          <td>
              <button class="btn btn-light" style="margin-top: 8px;" v-bind:disabled="!librarianAccountId || !librarianPassword"  @click="loginLibrarian(librarianAccountId, librarianPassword)">Login</button>
          </td>
      </tr>  
    </table>
    <!--
    Used to display an error when the login of a librarian is unsuccessful
    -->
    <p>
        <span v-if="errorLoginLibrarian" style="color:red">Error: {{errorLoginLibrarian}}</span>
    </p>
  </div>
  </div>    
</template>
<script src="./login.js">
</script>
<style>
</style>
