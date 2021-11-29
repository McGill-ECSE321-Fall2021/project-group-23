<template>
    <div id="Holiday" class="container-fluid">
         <h1 style="text-align:left">Current Holidays</h1>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Start</th>
                    <th>End</th>
                </tr>
                ​<v-tr v-for="holiday in holidays" :key="holiday.name" :row="holiday">
                    <td>{{ holiday.name }}</td>
                    <td>{{ holiday.startDate }}</td>
                    <td>{{ holiday.endDate }}</td>
                </v-tr>
            </table>
        <hr>
        <h2 style="text-align:left">Create Holiday</h2>
            <table>
                <tr>
                    <th>name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="cName">
                    </td>
                    <td>
                        <input type="date" v-model="cStartDate" >
                    </td>
                    <td>
                        <input type="date" v-model="cEndDate" >
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!cName || !cStartDate || !cEndDate" @click="createHoliday(cName, cStartDate, cEndDate)">Make Holiday</button>
                    </td>
                </tr>    
            </table>
            <p>
             <span v-if="errorHoliday" style="color:red"> {{errorHoliday}} </span>
            </p>
        <hr>
        <h2 style="text-align:left">Update Holiday</h2>
            <table>
                <tr>
                    <th>name</th>
                    <th>New Start Date</th>
                    <th>New End Date</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="uName">
                    </td>
                    <td>
                        <input type="date" v-model="uNewStartDate">
                    </td>
                    <td>
                        <input type="date" v-model="uNewEndDate">
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!uName || !uNewStartDate || !uNewEndDate" @click="updateHoliday(uName, uNewStartDate, uNewEndDate)">Update Holiday</button>
                    </td>
                </tr>    
            </table>
            <p>
            <span v-if="errorHoliday" style="color:red"> {{errorHoliday}} </span>
            </p>
        <hr>
        <h2 style="text-align:left">Delete Holiday</h2>
            <table>
                <tr>
                    <th>name</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="dName">
                    </td>
                </tr>
                <tr>
                    <td>
                    <button v-bind:disabled="!dName" @click="deleteHoliday(dName)">Delete Holiday</button>
                    </td>
                </tr>    
            </table>
        <p>
        <span v-if="errorHoliday" style="color:red"> {{errorHoliday}} </span>
        </p>
    </div>

</template>

<script src="./Holiday.js">
</script>

<style scoped>
select {
 height: 30px;
}
</style>