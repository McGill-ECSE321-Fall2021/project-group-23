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
            headLib: {},
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
        // Method for when table needs to be reset
        getAllLibrarians: function() {
            AXIOS.get('/getAllLibrarians')
            .then(response => {
                this.librarians = response.data
            })
            .catch(e => {
                this.errorLibrarian = e
            }) 
        },
        goToProfile: async function() {
            await AXIOS.get('/getLibrarianById/' + 1234)
            .then(response => {
                this.headLib = response.data
            })
            .catch(e => {
                this.errorLibrarian = e
            }) 
            this.$router.push({ path: `/Profile/` + this.headLib.firstName + '/' + this.headLib.lastName + '/' + 1234 + '/' + this.headLib.address + '/' + this.headLib.email + '/' + this.headLib.password + '/' + 'Head Librarian'})
        },
        goToRegularLibPage: function() { 
            this.$router.push({ path: `/LibrarianPage/` + 1234 })
        },
        createItem: function() {
            this.$router.push({ path: `/CreateItem` })
        },
        holidayStuff: function() {
            this.$router.push({ path: `/Holiday` })
        },
        openingHourStuff: function() {
            this.$router.push({ path: `/OpeningHours` })
        },
        // Method for editing head librarian info
        updateHeadInfo: function() {
            this.$router.push({ path: `/UpdateHeadLibrarian` })
        },
        // Method for switching page to edit schedule
        editSchedule: function(id) {
            if (id == undefined) {
                this.errorLibrarian = "Please select the librarian whose shifts you wish to edit."
            }
            else this.$router.push({ path: `/ScheduleAssignment/${id}` })
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
            // Create the weekly schedule and wait for response before creating Librarian
            await AXIOS.post('/createWeeklySchedule/'.concat(text), {}, {})
            .then(response => {
                this.schedules.push(response.data)
                lastSchedule = JSON.parse(JSON.stringify(this.schedules[this.schedules.length-1]))
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = 'Please add shifts to the new librarian first.'
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
        // Creates a new shift; takes params and inputs by itself
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