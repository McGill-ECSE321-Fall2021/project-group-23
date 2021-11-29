import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'Signup',

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
            errorSignupCustomer2: '',
            errorSignupCustomer3: '',
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
            this.errorSignupCustomer2=''
            this.errorSignupCustomer3=''
            var newCustomer = this.customerAccounts[this.customerAccounts.length-1]
            this.$router.push({ path: `/CustomerHomePage/` + newCustomer.firstName + '/' + newCustomer.lastName + '/' + newCustomer.customerId + '/' + newCustomer.address + '/' + newCustomer.email + '/' + newCustomer.password + '/Customer' })
        }).catch(e => {
            var errorMsg = "-Your first name and last name cannot contain numbers or special characters"
            var errorMsg2 = "-Your password must contain at least 8 characters and a capital letter"       
            var errorMsg3="-Otherwise, an account with this email already exists"
            console.log(errorMsg)
            this.errorSignupCustomer = errorMsg
            this.errorSignupCustomer2 = errorMsg2
            this.errorSignupCustomer3 = errorMsg3
            
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