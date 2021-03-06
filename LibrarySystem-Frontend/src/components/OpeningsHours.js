import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'OpeningsHours',

    created: function () {
        AXIOS.get('/getAllOpeningsHours')
            .then(response => {
                // JSON responses are automatically parsed.
                this.openingsHours = response.data
            })
            .catch(e => {
                this.errorOpeningsHours = e
            })
    },
    data() {
        return {
            errorOpeningsHours: '',
            cDay: '',
            cStartTime: '',
            cEndTime: '',
            uDay: '',
            uStartTime: '',
            uEndTime: '',
            dDay: '',
            openingsHours: [],
        }
    },

    methods: {
        getAllOpeningsHours: function () {
            AXIOS.get('/getAllOpeningsHours')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.openingsHours = response.data
                })
                .catch(e => {
                    this.errorOpeningsHours= "Error: failed to retrieve all opening hours"
                })
        },
        createOpeningsHours: function (day, startTime, endTime) {
            AXIOS.post('/createOpeningsHour/' + day + '?startTime=' + startTime + '&endTime=' + endTime).then(response => {
                this.getAllOpeningsHours()
                this.errorOpeningsHours= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOpeningsHours = "Error: failed to create opening hour"
            })

        },
        updateOpeningsHours: function (day, startTime, endTime) {
            AXIOS.put('/updateOpeningsHoursTimes/' + day + '?newStartTime=' + startTime + '&newEndTime=' + endTime).then(response => {
                this.getAllOpeningsHours()
                this.errorOpeningsHours= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOpeningsHours = "Error: failed to update opening hour"
            })

        },
        deleteOpeningsHours: function (day) {
            console.log(day)
            AXIOS.delete('/deleteOpeningsHours/' + day).then(response => {
                this.getAllOpeningsHours()
                this.errorOpeningsHours = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOpeningsHours = "Error: failed to delete opening hour"
            })

        },
    }
}