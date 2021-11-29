<template>
    <div id="OpeningsHours" class="container-fluid">
         <h1 style="text-align:left">Current Opening Hours</h1>
            <table>
                <tr>
                    <th>Day</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
                ​<v-tr v-for="openingsHour in openingsHours" :key="openingsHour.openingDay" :row="openingsHour">
                    <td>{{ openingsHour.openingDay }}</td>
                    <td>{{ openingsHour.startTime }}</td>
                    <td>{{ openingsHour.endTime }}</td>
                </v-tr>
            </table>
        <hr>
        <h2 style="text-align:left">Create Opening Hours</h2>
            <table>
                <tr>
                    <th>Day</th>
                    <th>Openening</th>
                    <th>Closing</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="cDay">
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
                    <th>Openening</th>
                    <th>Closing</th>
                </tr>
                <tr>
                     <td>
                        <input type="text" v-model="uDay">
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
                        <input type="text" v-model="dDay">
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
</style>
