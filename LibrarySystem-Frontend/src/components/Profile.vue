<template>
  <div id="profile">
    <div id="header">
      <h2>User Profile - {{ accountType }}</h2>
      <h2>{{ name + " " +familyname}}</h2>
      <hr />
    </div>
    <div id="body">
      <h4>Account ID : {{ id }}</h4>
      <h4>Email : {{ email }}</h4>
      <div id="password">
        <h4>Password : {{ password }}</h4>
      </div>
      <div id="address">
        <h4>Address : {{ address }}</h4>
      </div>
      <button type="button" class="btn btn-info" v-on:click="isShow = !isShow">
        Update Account
      </button>
      <button type="button" class="btn btn-danger" id="deleteAccountButton">
        Delete Account
      </button>
      <div v-show="isShow" id="update-account">
        <div>
          <input type="text" v-model="newAddress" placeholder="new address" />
          <button
            type="button"
            class="btn btn-light"
            v-bind:disabled="!newAddress"
            v-on:click="updateAddress(newAddress)"
          >
            Update
          </button>
        </div>

        <div>
          <input type="text" v-model="newPassword" placeholder="new Password" />
          <button
            type="button"
            class="btn btn-light"
            v-bind:disabled="!newPassword"
            v-on:click="updatePassword(newPassword)"
          >
            Update
          </button>
        </div>

        <div v-show="isError" id="error">
          <H5>Your password must have at least 8 characters and a capital letter</H5>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="./profile.js">
</script>
<style>
#update-account {
  margin-top: 10px;
}
#error {
  margin-top : 12px;
  color: rgb(184, 17, 17);
}
</style>