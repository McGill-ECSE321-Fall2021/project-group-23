import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    props: ["nameP", "familynameP", "idP", "addressP", "emailP", "passwordP", "accountTypeP"],
    data() {
        return {
            name: this.nameP,
            familyname: this.familynameP,
            address: this.addressP,
            email: this.emailP,
            password: this.passwordP,
            accountType: this.accountTypeP,
            newAddress: '',
            newPassword: '',
            id: this.idP,
            isShow: false,
            isError: false,
        };
    },
    methods: {
        updatePassword: function (pass) {
            AXIOS.put('/updateCustomer/' + this.id + "/" + pass + "/" + this.address, {}, {})
                .then((response) => {
                    this.isError = false
                    this.password = response.data.password;
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                    this.isError = true;
                });
        },
        updateAddress: function (addr) {
            AXIOS.put('/updateCustomer/' + this.id + "/" + this.password + "/" + addr, {}, {})
                .then((response) => {
                    this.address = response.data.address;
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                });
        },
    },
};