import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
    }
};

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = backendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'scheduleassignment',
    props: ['id'],
    data () {
        return {
            librarian: {},
            shifts: [],
            currentShifts: [],
            newShift: {
                day: '',
                startTime: '',
                endTime: '',
                id: ''
            },
            successShift: '',
            errorShift: '',
            errorLibrarian: '',
            response: []
        }
    },
    created: async function() {
        // Retrieve the right librarian's shifts (get librarian first)
        await AXIOS.get('/getLibrarianById/' + this.$props.id)
        .then(response => {
            this.librarian = response.data
        })
        .catch(e => {
            this.errorLibrarian = e
        })

        var x;
        for (x in this.librarian.weeklySchedule.shifts) {
            await AXIOS.get('/getShiftById/' + this.librarian.weeklySchedule.shifts[x].shiftId)
            .then(response => {
                this.currentShifts.push(response.data)
            })
            .catch(e => {
                this.errorShift = e
            })
        }
    },
    methods: {
        // method to return to the head librarian homepage
        returnToPrev: function() {
            this.$router.push({ path: `/LibrarianManagement/`})
        },
        // method to reset the shifts in the current shifts table
        resetShifts: function() {
            var x;
            this.currentShifts = [];
            // after emptying the array, add all the shifts added into currentShifts
            for (x in this.shifts) {
                this.currentShifts.push(this.shifts[x]);
            }
        },
        // creates a new shift with params from function params
        createShift: function (day, start, end) {
            AXIOS.post('/createShift', {}, {
                params: {
                    dayOfWeek: day,
                    startTime: start,
                    endTime: end
                }
            })
            .then(response => {
                this.shifts.push(response.data)
                this.errorShift = ''
                this.successShift = ''
            })
            .catch(e => {
                var errorMsg = 'Please ensure that the provided times are correct and within library opening hours.'
                console.log(errorMsg)
                this.errorShift = errorMsg
                this.successShift = ''
            })
        },
        // clear the table of all shifts
        deleteAllShifts: function() {
            this.shifts = []
            this.errorShift = ''
            this.successShift = ''
        },
        // updates the schedule after shifts are added
        updateWeeklySchedule: function() {
            // Loop through shifts added to use for URI request for weekly schedule
            var x;
            var shift;
            var text = "";

            for (x in this.shifts) {
                shift = this.shifts[x];
                text += shift.shiftId;
                text += ','
            }
            
            // removes the extra comma from the end of text
            text = text.substring(0, text.length - 1);

            // get the schedule id
            var scheduleId = this.librarian.weeklySchedule.weeklyScheduleId

            AXIOS.put('/updateWeeklyScheduleShifts/' + scheduleId + '/' + text)
            .then(response => {
                this.resetShifts()
                this.errorShift = ''
                this.successShift = 'Shift updated successfully!'
            })
            .catch(e => {
                var errorMsg = 'No shifts provided. Please try again.'
                console.log(errorMsg)
                this.errorShift = errorMsg
            })
        }
    }
}
