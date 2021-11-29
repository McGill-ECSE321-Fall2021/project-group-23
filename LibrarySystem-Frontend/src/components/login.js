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
        this.errorLoginCustomer = e
        }),
        AXIOS.get('/getAllLibrarians').then(response => {
        this.librarianAccounts = response.data
        })
        .catch(e => {
        this.errorLoginLibrarian = e
        }),
        AXIOS.get('/getAllLibrarians').then(response => {
        this.headLibrarianAccounts = response.data
        })
        .catch(e => {
        this.errorLoginHeadLibrarian = e
        })
    },

    data() {
        return {
            customerAccounts: [],
            librarianAccounts: [],
            customerAccountId: '',
            customerPassword: '',
            librarianAccountId: '',
            librarianPassword: '',
            errorLoginCustomer: '',
            errorLoginLibrarian: '',
            response: [],

        }
        
    },



    methods: {
        loginCustomer: function (customerAccountId, customerPassword) {
            AXIOS.get('/loginCustomer/' + customerAccountId + '/' + customerPassword).then(response => {
                this.customerAccounts.push(response.data)
                errorLoginCustomer= ''
                errorLoginLibrarian= ''
            }).catch(e => {
                var errorMsg = "Invalid accountId-password Combo"
                console.log(errorMsg)
                this.errorLoginCustomer = errorMsg
            })
        },

        loginLibrarian: function (librarianAccountId, librarianPassword) {
            AXIOS.get('/loginLibrarian/' + librarianAccountId + '/' + librarianPassword).then(response => {
                this.librarianAccounts.push(response.data)
                errorLoginCustomer= ''
                errorLoginLibrarian= ''
            }).catch(e => {
                var errorMsg = "Invalid accountId-password Combo"
                console.log(errorMsg)
                this.errorLoginLibrarian = errorMsg
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
        },
        getAllLibrarianAccounts: function () {
            AXIOS.get('/getAllLibrarians').then(response => {
                this.librarianAccounts = response.data
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorSignupCustomer = errorMsg
    
            })
        }

    }
}