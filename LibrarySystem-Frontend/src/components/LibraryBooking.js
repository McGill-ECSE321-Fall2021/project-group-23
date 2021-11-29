import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    props: ["customerIdP"],
    name: 'LibraryBooking',
    created: function() {
    },

    data() {
        return{
        id: this.customerIdP,
        cStartTime: '',
        cEndTime: '',
        cStartDate: '',
        }
    },

    methods: {
        createLibraryBooking: function(id, startDate, startTime, endTime) {
            console.log(this.id)
            AXIOS.post('/createLibraryBooking/' + id + "?startDate=" + startDate + "&endDate="+ startDate +"&startTime="+ startTime +"&endTime="+ endTime)
            .then((response) => {
                    this.isError = false
                    this.password = response.data.password;
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.isError = true;
                });
        }
    }
}