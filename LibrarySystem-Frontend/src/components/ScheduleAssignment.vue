<template>
    <div id="assign-schedule" class="container-fluid">
        <h1 style="text-align:center">Update Weekly Shifts</h1>
        <hr>
        <h2 style="text-align:center">Shifts to Assign</h2>
        <button class="btn btn-light" style="padding:5px" v-on:click="returnToPrev()">Return to Previous Page</button>
        <p> Please note resetting the shifts removes the previous ones. </p>
            <table class="centerTable">
                <tr>
                    <th>Day of Shift</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
                <tr>
                    <td>
                        <select name="Day of Week" id="day" v-model="newShift.day">
                            <option value="Monday">Monday</option>
                            <option value="Tuesday">Tuesday</option>
                            <option value="Wednesday">Wednesday</option>
                            <option value="Thursday">Thursday</option>
                            <option value="Friday">Friday</option>
                            <option value="Saturday">Saturday</option>
                            <option value="Sunday">Sunday</option>
                        </select>
                    </td>
                    <td>
                        <input type="time" id="starttime" v-model="newShift.startTime">
                    </td>
                    <td>
                        <input type="time" id="endtime" v-model="newShift.endTime">
                    </td>
                </tr>
            </table>
            <table class="centerTable">
                <tr>
                    <td></td>
                    <td> <button
                            style="margin-top: 8px" class="btn btn-light" v-bind:disabled="!newShift.day || !newShift.startTime || !newShift.endTime" 
                            v-on:click="createShift(newShift.day, newShift.startTime, newShift.endTime)">Add Shift to Table
                    </button> </td>
                </tr>
                <tr>
                    <td></td>
                    <td> <button
                            class="btn btn-light" style="margin-top: 8px" v-on:click="deleteAllShifts()">Clear Shifts
                    </button> </td>
                </tr>
                <tr>
                    <td></td>
                    <td> <button
                            style="margin-top: 8px" class="btn btn-light" v-on:click="updateWeeklySchedule()">Update Schedule
                    </button> </td>
                </tr>
            </table>
            <p>
                <span v-if="errorShift" style="text-align:center; color:red"> {{errorShift}} </span>
                <span v-else-if="successShift" style="text-align:center; color:green"> {{successShift}} </span>
            </p>
            <v-table :data="shifts" selectedClass="table-info" class="table-hover centerTable">
                <thead slot="head">
                    <th style="padding: 20px">Day of Week</th>
                    <th style="padding: 20px">Start Time</th>
                    <th style="padding: 20px">End Time</th>
                    <th style="padding: 20px">Shift ID</th>
                </thead>
                <tbody slot="body">
                    <v-tr v-for="shift in shifts" :key=shift.shiftId :row=shift.dayOfWeek>
                        <td> {{shift.dayOfWeek}} </td>
                        <td> {{shift.startTime}} </td>
                        <td> {{shift.endTime}} </td>
                        <td> {{shift.shiftId}} </td>
                    </v-tr>
                </tbody>
            </v-table>
        <hr>
        <h2 style="text-align:center">Current Shifts</h2>
            <v-table :data="currentShifts" selectedClass="table-info" class="table-hover centerTable">
                <thead slot="head">
                    <th style="padding: 20px">Day of Week</th>
                    <th style="padding: 20px">Start Time</th>
                    <th style="padding: 20px">End Time</th>
                    <th style="padding: 20px">Shift ID</th>
                </thead>
                <tbody slot="body">
                    <v-tr v-for="currShift in currentShifts" :key=currShift.shiftId :row=currShift.dayOfWeek>
                        <td> {{currShift.dayOfWeek}} </td>
                        <td> {{currShift.startTime}} </td>
                        <td> {{currShift.endTime}} </td>
                        <td> {{currShift.shiftId}} </td>
                    </v-tr>
                </tbody>
            </v-table> 
    </div>
</template>

<script src="./scheduleAssignment.js">
</script>

<style>

</style>