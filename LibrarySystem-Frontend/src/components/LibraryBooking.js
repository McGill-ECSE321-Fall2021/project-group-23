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

    created: function () {

        AXIOS.get('/getLibraryBookingByCustomer/' + this.id)
            .then((response) => {
                this.yourBookings = response.data

            })
            .catch((e) => {
                var errorMsg = e.response.data.message;
                console.log(errorMsg);
                this.errorBooking = errorMsg;
            }),
            AXIOS.get('/getAllLibraryBooking/')
                .then((response) => {
                    this.allBookings = response.data

                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.errorBooking = errorMsg;
                });


    },

    data() {
        return {
            id: this.customerIdP,
            cStartTime: '',
            cEndTime: '',
            cStartDate: '',
            yourBookings: [],
            errorBooking: '',
            allBookings: [],
            selectedBooking: []
        }
    },

    methods: {
        createLibraryBooking: function (id, startDate, startTime, endTime) {
            console.log(this.id)
            AXIOS.post('/createLibraryBooking/' + id + "?startDate=" + startDate + "&endDate=" + startDate + "&startTime=" + startTime + "&endTime=" + endTime)
                .then((response) => {
                    this.refresh()
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.errorBooking = "Unable to create booking, check for overlap.";
                });
        },
        updateLibraryBooking: function (id, startDate, startTime, endTime) {
            console.log(this.id)
            AXIOS.put('/updateLibraryBookingDateAndTime/' + id + "?startDate=" + startDate + "&endDate=" + startDate + "&startTime=" + startTime + "&endTime=" + endTime)
                .then((response) => {
                    this.refresh()
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.errorBooking = "Unable to modify booking, check for overlap.";
                });
        },
        deleteBooking: function (id) {
            
            AXIOS.delete('/deleteLibraryBooking/' + id )
                .then((response) => {
                this.refresh()
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.errorBooking = "Unable to delete, select a booking first";
                });
        },
        refresh: function () {

            AXIOS.get('/getLibraryBookingByCustomer/' + this.id)
                .then((response) => {
                    this.yourBookings = response.data
    
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.errorBooking = errorMsg;
                }),
                AXIOS.get('/getAllLibraryBooking/')
                    .then((response) => {
                        this.allBookings = response.data
    
                    })
                    .catch((e) => {
                        var errorMsg = e.response.data.message;
                        console.log(errorMsg);
                        this.errorBooking = errorMsg;
                    });
    
    
        },
    }
}