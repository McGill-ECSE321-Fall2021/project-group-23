import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'Login',

    created: function () {
        AXIOS.get('/getAllCustomers').then(response => {
        this.customerAccounts = response.data
        })
        .catch(e => {
        this.errorSignupCustomer = e
        })
    },
    data() {
        return {
            customerAccounts: [],
            firstName: '',
            lastName: '',
            email: '',
            address: '',
            password: '',
            errorSignupCustomer: '',
            response: [],

        }
        
    },



methods: {
    signupCustomer: function (firstName, lastName, address, email, password) {
        AXIOS.post('/createCustomer/' + firstName + '/' + lastName + '/' + password + '/' + email + '/false/false/' + address + '/0' ).then(response => {
            this.customerAccounts.push(response.data)
            this.firstName=''
            this.lastName=''
            this.email=''
            this.address=''
            this.password=''
            this.errorSignupCustomer=''
        }).catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorSignupCustomer = errorMsg
        })
    },

    getAllCustomerAccounts: function () {
        AXIOS.get('/getAllCustomers').then(response => {
            this.customerAccounts = response.data
        }).catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorSignupCustomer = errorMsg

        })
    }
    
}
}