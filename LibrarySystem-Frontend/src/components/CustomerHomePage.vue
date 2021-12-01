<template>
  <div id="customer-home-page">
    
      <button class="btn btn-light" v-on:click="goToProfile()">Profile</button>
      <button class="btn btn-light" v-on:click="goToReservations()">
        Reservations
      </button>
      <button class="btn btn-light">Balance</button>
      <button class="btn btn-light" v-on:click="goToBookings()">
        Library Bookings
      </button>
    <br>
      <table class="centerTable" style="margin-top: 30px">
        <h3>Browse books, movies, albums, newspapers and archives!</h3>
        <ViewItems></ViewItems>
      </table>
    </div>
  
</template>

<script src="./CustomerHomePage.js">
</script>

<style scoped>
</style>
