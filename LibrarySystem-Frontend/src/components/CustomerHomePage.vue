<template>
  <div id="customer-home-page">
    <div>
      <button class="btn btn-default" v-on:click="goToProfile()">Profile</button>
      <button class="btn btn-default" v-on:click="goToReservations()">Reservations</button>
      <button class="btn btn-default">Balance</button>
      <button class="btn btn-default">Library Bookings</button>
    </div>
    <form class="example" action="render_page.com">
      <input type="text" placeholder="Search item by title or id.." name="search">
      <button type="submit">Search</button>
    </form>
  </div>
</template>
<script src="./CustomerHomePage.js">
</script>
