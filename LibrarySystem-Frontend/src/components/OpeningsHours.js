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
                    this.errorOpeningsHours= e
                })
        },
        createOpeningsHours: function (day, startTime, endTime) {
            AXIOS.post('/createOpeningsHours/' + day + '/' + startTime + '/' + endTime).then(response => {
                this.getAllOpeningsHours()
                this.errorOpeningsHours= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOpeningsHours = errorMsg
            })

        },
        updateOpeningsHours: function (day, startTime, endTime) {
            AXIOS.put('/updateOpeningsHours/' + day + '/' + startTime + '/' + endTime).then(response => {
                this.getAllOpeningsHours()
                this.errorOpeningsHours= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOpeningsHours = errorMsg
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
                this.errorOpeningsHours = errorMsg
            })

        },
    }
}