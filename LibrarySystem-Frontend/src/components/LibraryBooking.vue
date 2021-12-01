<template>
  <div id="LibraryBooking">
    <p>
        <span v-if="errorBooking" style="color:red"> {{errorBooking}} </span>
    </p>
    <h2 style="text-align: center">Your Bookings</h2>
    <v-table
      :data="yourBookings"
      selectedClass="table-info"
      @selectionChanged="selectedBooking = $event"
      class="table-hover centerTable"
      style="margin-bottom: 10px;"
    >
      <thead slot="head" >
        <th style="padding: 15px">Date</th>
        <th style="padding: 15px">Start Time</th>
        <th style="padding: 15px">End Time</th>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <v-tr v-for="booking in displayData" :key="booking.id" :row="booking">
          <td>{{ booking.startDate }}</td>
          <td>{{ booking.startTime }}</td>
          <td>{{ booking.endTime }}</td>
        </v-tr>
      </tbody>
    </v-table>
    <button style="margin-bottom: 10px;" class="btn btn-light" v-bind:disabled="!selectedBooking[0]" @click="deleteBooking(selectedBooking[0].id)">Delete Selected Booking</button>
    <hr>
    <h2 style="text-align: center">All Library Bookings</h2>
    <v-table :data="allBookings" class="centerTable" style="margin-bottom: 10px;">
      <thead slot="head">
        <th style="padding: 15px">Date</th>
        <th style="padding: 15px">Start Time</th>
        <th style="padding: 15px">End</th>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <tr v-for="booking in displayData" :key="booking.id">
          <td>{{ booking.startDate }}</td>
          <td>{{ booking.startTime }}</td>
          <td>{{ booking.endTime }}</td>
        </tr>
      </tbody>
    </v-table>
    <hr>
    <h2 style="text-align: center">Create Library Booking</h2>
    <table class="centerTable" style="margin-bottom: 10px;">
      <tr>
        <th>Day</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
      <tr>
        <td>
          <input type="date" v-model="cStartDate" />
        </td>
        <td>
          <input type="time" v-model="cStartTime" />
        </td>
        <td>
          <input type="time" v-model="cEndTime" />
        </td>
      </tr>
      <tr>
        <td>
          <button
            class="btn btn-light" v-bind:disabled="!cEndTime || !cStartTime || !cStartDate"
            @click="createLibraryBooking(id, cStartDate, cStartTime, cEndTime)"
          >
            Make Library Booking
          </button>
        </td>
        <td>
          <button class="btn btn-light" v-bind:disabled="!selectedBooking[0]" @click="updateLibraryBooking(selectedBooking[0].id, cStartDate, cStartTime, cEndTime)">Modify Selected Booking</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script src="./LibraryBooking.js">
</script>

<style>
select {
  height: 30px;
}
v-table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
th,
td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #000;
  color: white;
}
input {
  width: 200px;
}
#itemm tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>