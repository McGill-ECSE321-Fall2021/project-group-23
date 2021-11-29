<template>
    <div id="assign-schedule" class="container-fluid">
        <h1 style="text-align:left">Update Weekly Shifts</h1>
        <hr>
        <h2 style="text-align:left">Shifts to Add</h2>
            <table>
                <tr>
                    <th>Day of Shift</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
                <tr>
                    <td>
                        <select name="Day of Week" id="day">
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
                        <input type="time" id="starttime">
                    </td>
                    <td>
                        <input type="time" id="endtime">
                    </td>
                </tr>
            </table>
            <p>
                <span v-if="errorShift" style="text-align:center; color:red"> {{errorShift}} </span>
            </p>
            <v-table :data="shifts" selectedClass="table-info" class="table-hover">
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
        <h2 style="text-align:left">Current Shifts</h2>
            <v-table :data="currentShifts" selectedClass="table-info" class="table-hover">
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