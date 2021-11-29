<template>
  <div id="LibraryBooking">
    <h2 style="text-align: left">Create Library Booking</h2>
    <table>
      <tr>
        <th>Day</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
      <tr>
        <td>
          <input type="date" v-model="cStartDate"/>
        </td>
        <td>
          <input type="time" v-model="cStartTime"/>
        </td>
        <td>
          <input type="time" v-model="cEndTime"/>
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
        </td>
      </tr>
    </table>
  </div>
</template>

<script src="./LibraryBooking.js">
</script>

<style>
</style>