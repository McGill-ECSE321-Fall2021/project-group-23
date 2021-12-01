import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'CreateBalance',
    props: ["customerIdP"],
    created: function () {
        AXIOS.get('/getCustomerById/' + this.customerId)
            .then(response => {
                // JSON responses are automatically parsed.
                this.customer = response.data
                this.balance = this.customer.accountBalance
            })
            .catch(e => {
                this.errorBalance = e
            })

    },
    data() {
        return {
            customerId: this.customerIdP,
            customer: {},
            errorBalance: '',
            balance: 0
        }
    },

   
}