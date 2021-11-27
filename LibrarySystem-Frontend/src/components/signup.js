import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function CustomerDto (firstName, lastName, address, email, password) {
    this.firstName=firstName
    this.lastName=lastName
    this.address=address
    this.email=email
    this.password=password
    this.accountBalance=0
    this.isLocal=false
    this.isVerified=false
}

export default {
    name: 'Login',

    created: function () {
        ​AXIOS.get('/perons') //modify this
        ​.then(response => {
          ​this.customerAccounts = response.data
        ​})
        ​.catch(e => {
          ​this.errorSignupCustomer = e
        ​})
    },
    data() {
        return {
            customerAccounts: [],
            newCustomerAccount: {
                firstName: '',
                lastName: '',
                email: '',
                address: '',
                password: '',
            },
            selectedCustomerAccount: {
                firstName: '',
                lastName: '',
                email: '',
                address: '',
                password: '',
            },
            errorSignupCustomer: '',
            response: [],

        }
        
    },



methods: {
    signupCustomer: function (firstName, lastName, address, email, password) {
        AXIOS.post('/createCustomer/' + firstName + '/' + lastName + '/' + password + '/' + email + '/false/false/' + address + '/0' ).then(response => {
            var customer = new  CustomerDto(firstName, lastName, address, email, password);
            this.customerAccounts.push(customer)
            this.newCustomerAccount.firstName=''
            this.newCustomerAccount.lastName=''
            this.newCustomerAccount.email=''
            this.newCustomerAccount.address=''
            this.newCustomerAccount.password=''
            this.errorSignupCustomer=''

            //add code here

        }).catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorItem = errorMsg
        })
    },

    getAllCustomerAccounts: function () {
        AXIOS.get('/getAllCustomers').then(response => {
            this.customerAccounts = response.data
        }).catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorItem = errorMsg

        })
    }
    
}
}