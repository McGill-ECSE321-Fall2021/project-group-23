<template>
  <div id="customer-home-page">
      <button class="btn btn-light" v-on:click="goToProfile()">Profile</button>
      <button class="btn btn-light" v-on:click="goToReservations()">Reservations</button>
      <button class="btn btn-light">Balance</button>
      <button class="btn btn-light" v-on:click="goToBookings()">Library Bookings</button>
    <div class="d-flex align-items-center justify-content-center"
  style="height: 250px;">
  <table>
  <h3>Browse books, movies, albums, newspapers and archives!</h3>
    <ViewItems></ViewItems>
    </table>
    </div>
  </div>
</template>

<script src="./CustomerHomePage.js">
</script>

<style scoped>
</style>
