<template>
    <div id="hire/fire-librarian" class="container-fluid">
        <h1 style="text-align:left">Head Librarian Management</h1>
        <button v-on:click="updateHeadInfo()">Update Head Librarian Information</button>
        <hr>

        <!-- Section for adding a new librarian; inputs are names and the password.-->
        <h2 style="text-align:left">Add Librarian</h2>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="newLibrarian.firstN" id="fname">
                    </td>
                    <td>
                        <input type="text" v-model="newLibrarian.lastN" id="lname">
                    </td>
                </tr>
                <tr>
                    <th>Password</th>
                </tr>
                <tr>
                    <td>
                        <input type="password" v-model="newLibrarian.pass" id="libpassword">
                    </td>
                    <td>
                        <button
                            v-bind:disabled="!newLibrarian.firstN || !newLibrarian.lastN || !newLibrarian.pass"
                            v-on:click="createLibrarian(newLibrarian.firstN, newLibrarian.lastN, newLibrarian.pass)">Add New Librarian
                        </button>
                    </td>
                </tr>
            </table>

        <!-- Section for assigning shifts to a new librarian; includes creation and deletion. -->
        <h3 style="text-align:left">Shifts to Assign</h3>
            <table>
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
                        <input type="time" v-model="newShift.startTime" id="starttime">
                    </td>
                    <td>
                        <input type="time" v-model="newShift.endTime" id="endtime">
                    </td>
                </tr>
            </table>
            <table style="text-align:left">
                <tr>
                    <td></td>
                    <td> <button
                            v-bind:disabled="!newShift.day || !newShift.startTime || !newShift.endTime" 
                            v-on:click="createShift(newShift.day, newShift.startTime, newShift.endTime)">Add Shift to Table
                    </button> </td>
                </tr>
                <tr>
                    <td></td>
                    <td> <button
                            v-on:click="deleteAllShifts()">Clear Shifts
                    </button> </td>
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

        <!-- Table of current librarians in the system -->
        <h2 style="text-align:left">Current Librarians</h2>
            <table style="text-align:left">
                <tr>
                    <td>
                        <button 
                            v-on:click="deleteLibrarian(selectedLibrarian[0])">Remove Librarian</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button v-on:click="editSchedule(selectedLibrarian[0])">View/Edit Weekly Schedule</button>
                    </td>
                </tr>
            </table>
            <p>
                <span v-if="errorLibrarian" style="color:red">{{errorLibrarian}} </span>
            </p>
            <v-table :data="librarians" selectedClass="table-info" @selectionChanged="selectedLibrarian = $event" class="table-hover">
                <thead slot="head">
                    <th style="padding: 20px">First Name</th>
                    <th style="padding: 20px">Last Name</th>
                    <th style="padding: 20px">Librarian ID</th>
                </thead>
                <tbody slot="body">
                    <v-tr v-for="librarian in librarians" :key=librarian.librarianId :row=librarian.librarianId>
                        <td> {{librarian.firstName}} </td>
                        <td> {{librarian.lastName}} </td>
                        <td> {{librarian.librarianId}} </td>
                    </v-tr>
                </tbody>
            </v-table>
    </div>    
</template>

<script src="./librarianManager.js"></script>

<style>

</style>