<template>
  <div id="customer-home-page" >
    <div>
      <button class="btn btn-default" v-on:click="goToProfile()">Profile</button>
      <button class="btn btn-default" v-on:click="goToReservations()">Reservations</button>
      <button class="btn btn-default">Balance</button>
      <button class="btn btn-default" v-on:click="goToBookings()">Library Bookings</button>
    </div>
    <div class="border d-flex align-items-center justify-content-center"
  style="height: 750px;">
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
