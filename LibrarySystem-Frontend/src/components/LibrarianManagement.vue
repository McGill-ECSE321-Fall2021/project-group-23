<template>
    <div id="hire/fire-librarian" class="container-fluid">
        <h1 style="text-align:left">Librarian Management</h1>
        <hr>
        <h2 style="text-align:left">Add Librarian</h2>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="fname">
                    </td>
                    <td>
                        <input type="text" id="lname">
                    </td>
                </tr>
                <tr>
                    <th>Password</th>
                </tr>
                <tr>
                    <td>
                        <input type="password" id="libpassword">
                    </td>
                </tr>
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
                <tr>
                    <td></td>
                    <td> <button v-on:click="createShift(newShift.day, newShift.startTime, newShift.endTime)">Add Shift to Table</button> </td>
                </tr>
            </table>
            <span v-if="errorShift" style="color:red"> {{errorShift}} </span>
        <h3 style="text-align:left">Shifts to Assign</h3>
            <table>
                <tr>
                    <td> <th style="padding: 20px">Day of Week</th> </td>
                    <td> <th style="padding: 20px">Start Time</th> </td>
                    <td> <th style="padding: 20px">End Time</th> </td>
                    <td> <th style="padding: 20px">Shift ID</th> </td>
                </tr>
                <tr v-for="shift in shifts" :key=shift>
                    <td> {{shift.dayOfWeek}} </td>
                    <td> {{shift.startTime}} </td>
                    <td> {{shift.endTime}} </td>
                    <td> {{shift.shiftId}} </td>
                </tr>
            </table> 
        <hr>
        <h2 style="text-align:left">Current Librarians</h2>
            <table>
                <tr>
                    <td> <th style="padding: 20px">First Name</th> </td>
                    <td> <th style="padding: 20px">Last Name</th> </td>
                    <td> <th style="padding: 20px">Librarian ID</th> </td>
                </tr>
                <tr v-for="librarian in librarians" :key=librarian>
                    <td> {{librarian.fName}} </td>
                    <td> {{librarian.lName}} </td>
                    <td> {{librarian.libId}} </td>
                </tr>
                <tr>
                    <td>
                        <button>Remove Librarian</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button>View/Edit Weekly Schedule</button>
                    </td>
                </tr>
            </table>

    </div>    
</template>

<script src="./librarianManager.js"></script>

<style>
</style>