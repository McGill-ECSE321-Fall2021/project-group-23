import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'Holiday',

    created: function () {
        AXIOS.get('/getAllHolidays')
            .then(response => {
                // JSON responses are automatically parsed.
                this.holidays = response.data
            })
            .catch(e => {
                this.errorHoliday = e
            })
    },
    data() {
        return {
            errorHoliday: '',
            cName: '',
            cStartDate: '',
            cEndDate: '',
            uName: '',
            uStartDate: '',
            uEndDate: '',
            dName: '',
            holidays: [],
        }
    },

    methods: {
        getAllHolidays: function () {
            AXIOS.get('/getAllHolidays')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.holidays = response.data
                })
                .catch(e => {
                    this.errorHoliday= e
                })
        },
        createHoliday: function (name, startDate, endDate) {
            AXIOS.post('/createHoliday/' + name + '/' + startDate + '/' + endDate).then(response => {
                this.getAllHolidays()
                this.errorHoliday= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorHoliday = errorMsg
            })

        },
        updateHoliday: function (name, startDate, endDate) {
            AXIOS.put('/updateHoliday/' + name + '/' + startDate + '/' + endDate).then(response => {
                this.getAllHolidays()
                this.errorHoliday= ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorHoliday = errorMsg
            })

        },
        deleteHOliday: function (name) {
            console.log(name)
            AXIOS.delete('/deleteHoliday/' + name).then(response => {
                this.getAllHolidays()
                this.errorHoliday = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorHoliday = errorMsg
            })

        },
    }
}