import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0');
var yyyy = today.getFullYear();

today = yyyy + '-' + mm + '-' + dd;

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'CreateReservation',
    props: ["customerIdP"],
    created: function () {
        AXIOS.get('/getAllItems')
            .then(response => {
                // JSON responses are automatically parsed.
                this.items = response.data
            })
            .catch(e => {
                this.errorReservation = e
            }),
        AXIOS.get('/getReservationByCustomer/' + this.customerId)
            .then(response => {
                // JSON responses are automatically parsed.
                this.reservations = response.data
            })
            .catch(e => {
                this.errorReservation = e
        })
    },
    data() {
        return {
            customerId: this.customerIdP,
            date: today,
            errorReservation: '',
            items: [],
            reservations: [],
            selectedItem: [],
            selectedReservation: [],
            filters: {
                title: { value: '', keys: ['title'] },
                type: {value: '', custom: this.typeFilter}
            },
            currentPage: 1,
            totalPages: 0
        }
    },

    methods: {
        createReservation: function (customerId, itemId, isCheckedOut, date) {
          AXIOS.post('/createReservation/' + customerId + '/' + itemId + '/' + isCheckedOut + '/' + date).then(response => {
            this.reservations.push(response.data)
            this.errorReservation = ''
          }).catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
          })
        },

        deleteReservation: function (reservationId) {
            AXIOS.delete('/deleteReservation/' + reservationId).then(response => {
                this.reservations.delete(response.data)
                this.errorReservation = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorReservation = errorMsg
            })
        },

        typeFilter (filterValue, row) {
            return (row.type == "Book" || row.type == "Movie" || row.type == "Album")
        }


    
      }
}