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

var backendUrl = backendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl
})

// Data transfer objects for Shift and Librarian
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

export default {
    name: 'librarianmanagement',
    data () {
        return {
            librarians: [],
            shifts: [],
            newLibrarian: '',
            newShift: {
                day: '',
                startTime: '',
                endTime: '',
                id: ''
            },
            selectedLibrarian: '',
            selectedShift: '',
            errorLibrarian: '',
            errorShift: '',
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
        // Initialize shifts
        AXIOS.get('/getAllShifts')
        .then(response => {
            this.shifts = response.data
        })
        .catch(e => {
            this.errorShift = e
        })
    },
    methods: {
        createLibrarian: function (librarianFName, librarianLName, librarianPass) {
            AXIOS.post('/createLibrarian/'.concat(librarianFName + '/' + librarianLName + '/' + librarianPass))
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
        }
    }
}