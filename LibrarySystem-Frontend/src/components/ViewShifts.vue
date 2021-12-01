<<template>
    <div id="viewshifts" class="container-fluid">
        <h2 style="text-align:center">Current Shifts</h2>
        <v-table :data="currentShifts" class="table-hover centerTable">
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

<script src="./ViewShifts.js">

</script>

<style>

</style>

