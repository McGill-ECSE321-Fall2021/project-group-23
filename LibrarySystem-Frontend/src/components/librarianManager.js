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

// Data transfer objects for Shift, Librarian and WeeklySchedule
function LibrarianDto (id, firstName, lastName, password) {
    this.libId = id
    this.fName = firstName
    this.lName = lastName
    this.pass = password
}

function ShiftDto(day, startTime, endTime, id) {
    this.dayOfWeek = day
    this.start = startTime
    this.end = endTime
    this.shiftId = id
}

function WeeklyScheduleDto(id, shifts) {
    this.scheduleId = id
    this.schedShifts = shifts
}

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
            selectedLibrarian: '',
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
        createLibrarian: function (librarianFName, librarianLName, librarianPass) {
            // Loop through shifts added to use for URI request for weekly schedule
            var x;
            var shift;
            var text = "";

            for (x in this.shifts) {
                shift = this.shifts[x];
                text += shift.shiftId;
                text += ','
            }

            text = text.substring(0, text.length - 1);

            AXIOS.post('/createWeeklySchedule/'.concat(text), {}, {})
            .then(response => {
                this.schedules.push(response.data)
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
            
            // If no error is thrown create the librarian
            // if (this.errorLibrarian.length == 0) {
                AXIOS.post('/createLibrarian/'.concat(librarianFName + '/' + librarianLName + '/' + librarianPass + '/' + this.schedules[this.schedules.length-1].weeklyScheduleId), {}, {})
                .then(response => {
                    this.librarians.push(response.data)
                    this.errorLibrarian = ''
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorLibrarian = errorMsg
                })
            // }
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
                this.errorShift = errorMsg
            })
        },
        deleteLibrarian: function(id) {
            // Delete the librarian first
            AXIOS.delete('/deleteLibrarian/' + id, {}, {})
            .then(response => {
                this.getAllLibrarians()
                this.deletedLibrarian.push(response.data)
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
            // Delete associated weekly schedule and shifts
            var arr = this.deletedLibrarian
            arr = JSON.parse(JSON.stringify(arr))
            AXIOS.delete('/deleteWeeklySchedule/' + arr.weeklySchedule.weeklyScheduleId, {}, {})
            .then(response => {
                this.deletedWeeklySchedule.push(response.data)
                this.errorLibrarian = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
            for (x in this.deletedWeeklySchedule.shifts) {
                AXIOS.delete('/deleteShiftById/' + this.deletedWeeklySchedule.shifts[x], {}, {})
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