<template>
  <div id="LibraryBooking">
    <p>
        <span v-if="errorBooking" style="color:red"> {{errorBooking}} </span>
    </p>
    <h2 style="text-align: left">Your Bookings</h2>
    <v-table
      :data="yourBookings"
      selectedClass="table-info"
      @selectionChanged="selectedBooking = $event"
      class="table-hover"
    >
      <thead slot="head">
        <th>DATE</th>
        <th>START TIME</th>
        <th>END TIME</th>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <v-tr v-for="booking in displayData" :key="booking.id" :row="booking">
          <td>{{ booking.startDate }}</td>
          <td>{{ booking.startTime }}</td>
          <td>{{ booking.endTime }}</td>
        </v-tr>
      </tbody>
    </v-table>
    <button v-bind:disabled="!selectedBooking[0]" @click="deleteBooking(selectedBooking[0].id)">Delete Selected Booking</button>
    <h2 style="text-align: left">All Library Bookings</h2>
    <v-table :data="allBookings">
      <thead slot="head">
        <th>DATE</th>
        <th>START TIME</th>
        <th>END TIME</th>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <tr v-for="booking in displayData" :key="booking.id">
          <td>{{ booking.startDate }}</td>
          <td>{{ booking.startTime }}</td>
          <td>{{ booking.endTime }}</td>
        </tr>
      </tbody>
    </v-table>
    <h2 style="text-align: left">Create Library Booking</h2>
    <table>
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
            v-bind:disabled="!cEndTime || !cStartTime || !cStartDate"
            @click="createLibraryBooking(id, cStartDate, cStartTime, cEndTime)"
          >
            Make Library Booking
          </button>
          <button v-bind:disabled="!selectedBooking[0]" @click="updateLibraryBooking(selectedBooking[0].id, cStartDate, cStartTime, cEndTime)">Modify Selected Booking</button>
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