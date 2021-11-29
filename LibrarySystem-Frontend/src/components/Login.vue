<template>
  <div id="Login">
  <div id="LoginCustomer">
    <table>
      <h2>Customer Login</h2>
      <tr>
          <td>
              <input type="text" v-model="customerAccountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="customerPassword" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button v-bind:disabled="!customerAccountId || !customerPassword"  @click="loginCustomer(customerAccountId, customerPassword)">Login</button>
          </td>
      </tr>
      <tr>
          <td>
              <button>Forgot password?</button>
          </td>
      </tr>
      <tr>
          <td>
              <button>Create new Account</button>
          </td>
      </tr>  
    </table>
    <p>
        <span v-if="errorLoginCustomer" style="color:red">Error: {{errorLoginCustomer}}</span>
    </p>
  </div>
  <div id="loginLibrarian">
  <table>
      <h2>Librarian Login</h2>
      <tr>
          <td>
              <input type="text" v-model="librarianAccountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="librarianPassword" placeholder="Password">
          </td>
      </tr>
      <!--
    These three spans are used to display an error message when the the signupCustomer function is not successful
    -->
      <tr>
          <td>
              <button v-bind:disabled="!librarianAccountId || !librarianPassword"  @click="loginLibrarian(librarianAccountId, librarianPassword)">Login</button>
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
