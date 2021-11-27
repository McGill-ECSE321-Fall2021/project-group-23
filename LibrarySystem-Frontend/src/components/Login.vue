<template>
  <div id="Login">
  <div id="LoginCustomer">
    <table>
      <h2>Customer Login</h2>
      <tr>
          <td>
              <input type="text" v-model="accountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="password" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button v-bind:disabled="!accountId || !password"  @click="loginCustomer(accountId, password)">Login</button>
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
              <input type="text" v-model="accountId" placeholder="AccountId">
          </td>
      </tr>
      <tr>
          <td>
              <input type="text" v-model="password" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button v-bind:disabled="!accountId || !password"  @click="loginLibrarian(accountId, password)">Login</button>
          </td>
      </tr>  
    </table>
    <p>
        <span v-if="errorLoginLibrarian" style="color:red">Error: {{errorLoginLibrarian}}</span>
    </p>
  </div>
  <div id="loginHeadLibrarian">
  <table>
      <h2>Head Librarian Login</h2>
      <tr>
          <td>
              <input type="text" v-model="password" placeholder="Password">
          </td>
      </tr>
      <tr>
          <td>
              <button v-bind:disabled="!password"  @click="loginHeadLibrarian(password)">Login</button>
          </td>
      </tr>  
    </table>
    <p>
        <span v-if="errorLoginHeadLibrarian" style="color:red">Error: {{errorLoginHeadLibrarian}}</span>
    </p>
  </div>
  </div>    
</template>
<script src="./login.js">
</script>
<style>
</style>
