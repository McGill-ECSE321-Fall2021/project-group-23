<template>
  <div id="Holiday" class="container-fluid">
    <h1 style="text-align: left">Current Holidays</h1>
    <v-table :data="holidays">
      <thead slot="head">
        <th>NAME</th>
        <th>START DATE</th>
        <th>END DATE</th>
      </thead>
      <tbody slot="body">
        <tr v-for="holiday in holidays" :key="holiday.name">
          <td>{{ holiday.name }}</td>
          <td>{{ holiday.startDate }}</td>
          <td>{{ holiday.endDate }}</td>
        </tr>
      </tbody>
    </v-table>
    <hr />
    <h2 style="text-align: left">Create Holiday</h2>
    <table>
      <tr>
        <th>name</th>
        <th>Start Date</th>
        <th>End Date</th>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="cName" />
        </td>
        <td>
          <input type="date" v-model="cStartDate" />
        </td>
        <td>
          <input type="date" v-model="cEndDate" />
        </td>
      </tr>
      <tr>
        <td>
          <button
            v-bind:disabled="!cName || !cStartDate || !cEndDate"
            @click="createHoliday(cName, cStartDate, cEndDate)"
          >
            Make Holiday
          </button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorHoliday" style="color: red"> {{ errorHoliday }} </span>
    </p>
    <hr />
    <h2 style="text-align: left">Update Holiday</h2>
    <table>
      <tr>
        <th>name</th>
        <th>New Start Date</th>
        <th>New End Date</th>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="uName" />
        </td>
        <td>
          <input type="date" v-model="uNewStartDate" />
        </td>
        <td>
          <input type="date" v-model="uNewEndDate" />
        </td>
      </tr>
      <tr>
        <td>
          <button
            v-bind:disabled="!uName || !uNewStartDate || !uNewEndDate"
            @click="updateHoliday(uName, uNewStartDate, uNewEndDate)"
          >
            Update Holiday
          </button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorHoliday" style="color: red"> {{ errorHoliday }} </span>
    </p>
    <hr />
    <h2 style="text-align: left">Delete Holiday</h2>
    <table>
      <tr>
        <th>name</th>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="dName" />
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!dName" @click="deleteHoliday(dName)">
            Delete Holiday
          </button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorHoliday" style="color: red"> {{ errorHoliday }} </span>
    </p>
  </div>
</template>

<script src="./Holiday.js">
</script>

<style scoped>
select {
  height: 30px;
}

v-table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  border: 1px solid #ddd;
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #000;
  color: white;
}

</style>

