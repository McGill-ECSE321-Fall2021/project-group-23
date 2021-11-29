import axios from 'axios'
import { response } from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'CreateReservation',

  created: function () {
      AXIOS.get('/getAllReservation')
          .then(response => {
              // JSON responses are automatically parsed.
              this.reservations = response.data
          })
          .catch(e => {
              this.errorItem = e
          })
  },

  data() {
    return {
      reservations: [],
      newReservation: '',
      errorReservation: '',
      response: []
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

  }

}