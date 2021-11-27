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
        ​AXIOS.get('/perons') //modify this
        ​.then(response => {
          ​this.customerAccounts = response.data
        ​})
        ​.catch(e => {
          ​this.errorLoginCustomer = e
        ​})
        ​AXIOS.get('/events') // modify this
        ​.then(response => {
     ​   this.librarianAccounts = response.data
        ​})
   ​     .catch(e => {
     ​   this.errorLoginLibrarian = e
        })
        AXIOS.get('/events') //modify this
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
            this.errorItem = errorMsg
        })
    },

    loginLibrarian: function (accountId, password) {
        AXIOS.get('/loginCustomer/' + accountId + '/' + password).then(response => {
            //add code here
        })
    },

    loginHeadLibrarian: function (password) {
        AXIOS.get('/loginCustomer/' + password).then(response => {
            //add code here
        })
        
    }
}
}