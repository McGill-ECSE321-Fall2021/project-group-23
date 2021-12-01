<template>
  <div id="Holiday" class="container-fluid">
    <h1 style="text-align: center">Current Holidays</h1>
    <v-table :data="holidays" class="centerTable">
      <thead slot="head">
        <th style="padding: 17px">Name</th>
        <th style="padding: 17px">Start Date</th>
        <th style="padding: 17px">End Date</th>
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
    <h2 style="text-align: center">Create Holiday</h2>
    <table class="centerTable">
      <tr>
        <th>Name</th>
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
            class="btn btn-light" v-bind:disabled="!cName || !cStartDate || !cEndDate"
            @click="createHoliday(cName, cStartDate, cEndDate)"
          >
            Add Holiday
          </button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorHoliday" style="color: red"> {{ errorHoliday }} </span>
    </p>
    <hr />
    <h2 style="text-align: center">Update Holiday</h2>
    <table class="centerTable">
      <tr>
        <th>Name</th>
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
            class="btn btn-light" v-bind:disabled="!uName || !uNewStartDate || !uNewEndDate"
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
    <h2 style="text-align: center">Delete Holiday</h2>
    <table class="centerTable">
      <tr>
        <th>Name</th>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="dName" />
        </td>
      </tr>
      <tr>
        <td>
          <button class="btn btn-light" v-bind:disabled="!dName" @click="deleteHoliday(dName)">
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

