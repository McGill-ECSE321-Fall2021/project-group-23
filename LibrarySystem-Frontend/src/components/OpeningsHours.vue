<template>
    <div id="OpeningsHours" class="container-fluid">
         <h1 style="text-align:left">Current Opening Hours</h1>
 <v-table :data="openingsHours">
      <thead slot="head">
        <th>DAY</th>
        <th>START TIME</th>
        <th>END TIME</th>
      </thead>
      <tbody slot="body">
        <tr v-for="openingsHour in openingsHours" :key="openingsHour.openingDay">
          <td>{{ openingsHour.openingDay }}</td>
          <td>{{ openingsHour.startTime }}</td>
          <td>{{ openingsHour.endTime }}</td>
        </tr>
      </tbody>
    </v-table>
        <h2 style="text-align:left">Create Opening Hours</h2>
            <table>
                <tr>
                    <th>Day</th>
                    <th>Opening</th>
                    <th>Closing</th>
                </tr>
                <tr>
                    <td>
                        ​<select type="text" v-model="cDay">
                            ​<option disabled selected value="">Select Type</option>
                            <option >Monday</option>
                            <option >Tuesday</option>
                            <option >Wednesday</option>
                            <option >Thursay</option>
                            <option >Friday</option>
                            <option >Saturday</option>
                            <option >Sunday</option>
                        </select>
                    </td>
                    <td>
                        <input type="time" v-model="cStartTime" >
                    </td>
                    <td>
                        <input type="time" v-model="cEndTime" >
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!cDay|| !cStartTime || !cEndTime" @click="createOpeningsHours(cDay, cStartTime, cEndTime)">Make Opening Hours</button>
                    </td>
                </tr>    
            </table>
            <p>
             <span v-if="errorOpeningsHours" style="color:red"> {{errorOpeningsHours}} </span>
            </p>
        <hr>
        <h2 style="text-align:left">Update Opening Hours</h2>
            <table>
                <tr>
                     <th>Day</th>
                    <th>Opening</th>
                    <th>Closing</th>
                </tr>
                <tr>
                    <td>
                        ​<select type="text" v-model="uDay">
                            ​<option disabled selected value="">Select Type</option>
                            <option >Monday</option>
                            <option >Tuesday</option>
                            <option >Wednesday</option>
                            <option >Thursay</option>
                            <option >Friday</option>
                            <option >Saturday</option>
                            <option >Sunday</option>
                        </select>
                    </td>
                    <td>
                        <input type="time" v-model="uStartTime" >
                    </td>
                    <td>
                        <input type="time" v-model="uEndTime" >
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!uDay|| !uStartTime || !uEndTime" @click="updateOpeningsHours(uDay, uStartTime, uEndTime)">Update Opening Hours</button>
                    </td>
                </tr>    
            </table>
            <p>
             <span v-if="errorOpeningsHours" style="color:red"> {{errorOpeningsHours}} </span>
            </p>
        <hr>
         <h2 style="text-align:left">Delete Opening Hours</h2>
            <table>
                <tr>
                     <th>Day</th>
                </tr>
                <tr>
                    <td>
                        ​<select type="text" v-model="dDay">
                            ​<option disabled selected value="">Select Type</option>
                            <option >Monday</option>
                            <option >Tuesday</option>
                            <option >Wednesday</option>
                            <option >Thursay</option>
                            <option >Friday</option>
                            <option >Saturday</option>
                            <option >Sunday</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!dDay" @click="deleteOpeningsHours(dDay)">delete Opening Hours</button>
                    </td>
                </tr>    
            </table>
            <p>
             <span v-if="errorOpeningsHours" style="color:red"> {{errorOpeningsHours}} </span>
            </p>
    </div>

</template>

<script src="./OpeningsHours.js">
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
