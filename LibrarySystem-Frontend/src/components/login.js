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
        ​AXIOS.get('/getAllCustomers') 
        ​.then(response => {
          ​this.customerAccounts = response.data
        ​})
        ​.catch(e => {
          ​this.errorLoginCustomer = e
        ​})
        ​AXIOS.get('/getAllLibrarians') 
        ​.then(response => {
     ​   this.librarianAccounts = response.data
        ​})
   ​     .catch(e => {
     ​   this.errorLoginLibrarian = e
        })
        AXIOS.get('/getAllLibrarians') //might lead to an error
        ​.then(response => {
     ​   this.headLibrarianAccounts = response.data
        ​})
   ​     .catch(e => {
     ​   this.errorLoginHeadLibrarian = e
        })
    },

    data() {
        return {
            customerAccounts: [],
            librarianAccounts: [],
            headLibrarianAccounts: [],
            newCustomerAccount: {
                accountId: '',
                password: '',
            },
            newLibrarianAccount: {
                accountId: '',
                password: '',
            },
            selectedCustomerAccount: {
                accountId: '',
                password: '',
            },
            selectedLibrarianAccount: {
                accountId: '',
                password: '',
            },
            newHeadLibrarianAccount: {
                password: '',
            },
            selectedHeadLibrarianAccount: {
                accountId: '',
                password: '',
            },
            errorLoginCustomer: '',
            errorLoginLibrarian: '',
            errorLoginHeadLibrarian: '',
            response: [],

        }
        
    },



    methods: {
        loginCustomer: function (accountId, password) {
            AXIOS.get('/loginCustomer/' + accountId + '/' + password).then(response => {
                //add code here
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLoginCustomer = errorMsg
            })
        },

        loginLibrarian: function (accountId, password) {
            AXIOS.get('/loginCustomer/' + accountId + '/' + password).then(response => {
                //add code here
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLoginLibrarian = errorMsg
            })
        },

        loginHeadLibrarian: function (password) {
            AXIOS.get('/loginCustomer/' + password).then(response => {
                //add code here
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLoginHeadLibrarian = errorMsg
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
                this.headlibrarianAccounts = response.data
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorSignupCustomer = errorMsg
    
            })
        }

    }
}