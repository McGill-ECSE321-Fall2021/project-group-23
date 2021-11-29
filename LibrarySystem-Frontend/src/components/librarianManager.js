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
    name: 'librarianmanagement',
    data () {
        return {
            librarians: [],
            shifts: [],
            schedules: [],
            newLibrarian: {
                firstN: '',
                lastN: '',
                pass: '',
                id: ''
            },
            newShift: {
                day: '',
                startTime: '',
                endTime: '',
                id: ''
            },
            errorLibrarian: '',
            errorShift: '',
            deletedLibrarian: [],
            deletedWeeklySchedule: [],
            response: []
        }
    },
    created: function () {
        // // Test data
        // const p1 = new LibrarianDto(12, 'Mac', 'Donald', 'ummm1')
        // this.librarians = [p1]

        // Initialize librarians from backend
        AXIOS.get('/getAllLibrarians')
        .then(response => {
            this.librarians = response.data
        })
        .catch(e => {
            this.errorLibrarian = e
        })
        // Initialize schedules
        AXIOS.get('/getAllWeeklySchedules')
        .then(response => {
            this.schedules = response.data
        })
        .catch(e => {
            this.errorLibrarian = e
        })
    },
    methods: {
        getAllLibrarians: function() {
            AXIOS.get('/getAllLibrarians')
            .then(response => {
                this.librarians = response.data
            })
            .catch(e => {
                this.errorLibrarian = e
            }) 
        },
        // Method for switching page to edit schedule
        go: function(id) {
            var path = id
            this.$router.push({ path: `/ScheduleAssignment/${id}` })
        },
        // async required to wait for post request for schedule before creating librarian
        createLibrarian: async function (librarianFName, librarianLName, librarianPass) {
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
            
            var lastSchedule;
            await AXIOS.post('/createWeeklySchedule/'.concat(text), {}, {})
            .then(response => {
                this.schedules.push(response.data)
                lastSchedule = JSON.parse(JSON.stringify(this.schedules[this.schedules.length-1]))
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })

            AXIOS.post('/createLibrarian/'.concat(librarianFName + '/' + librarianLName + '/' + librarianPass + '/' + lastSchedule.weeklyScheduleId), {}, {})
            .then(response => {
                this.librarians.push(response.data)
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
        },
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
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorShift = 'Please ensure that the provided times are correct and within library opening hours.'
            })
        },
        // await and async for step-by-step process in deletion
        deleteLibrarian: async function(id) {
            // Delete the librarian first and store in variable
            var removedLib;
            await AXIOS.delete('/deleteLibrarian/' + id, {}, {})
            .then(response => {
                this.getAllLibrarians()
                this.deletedLibrarian.push(response.data)
                removedLib = JSON.parse(JSON.stringify(this.deletedLibrarian[this.deletedLibrarian.length-1]))
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = 'Librarian account to delete not selected.'
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
            
            // Internal deletions
            // Delete associated weekly schedule and store in variable...
            var removedSched;
            await AXIOS.delete('/deleteWeeklySchedule/' + removedLib.weeklySchedule.weeklyScheduleId, {}, {})
            .then(response => {
                this.deletedWeeklySchedule.push(response.data)
                removedSched = JSON.parse(JSON.stringify(this.deletedWeeklySchedule[this.deletedWeeklySchedule.length-1]))
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })

            //.. and loop through shifts to delete each one with its id
            for (x in this.deletedWeeklySchedule.shifts) {
                AXIOS.delete('/deleteShiftById/' + removedSched.shifts[x].shiftId, {}, {})
                .then(response => {
                    this.errorLibrarian = ''
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorLibrarian = errorMsg
                })
            } 
        },
        deleteAllShifts: function() {
            this.shifts = []
            this.errorShift = ''
        }
    }
}