import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function () {
    switch (process.env.NODE_ENV) {
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort;
    }
};

var backendUrl = backendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl,
    //headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    props: ["nameP","familynameP", "idP", "addressP", "emailP", "passwordP", "accountTypeP"],
    data() {
        return {
            name: this.nameP,
            familyname: this.familynameP,
            address: this.addressP,
            email: this.emailP,
            password: this.passwordP,
            accountType: this.accountTypeP,
            id : this.idP,
            newAddress: "",
            newPassword: "",
            isShow: false,
        };
    },
    methods: {
        updatePassword: function (pass) {
            AXIOS.put("/updateCustomer/" + id + "/" + { newPassword } + "/" + { address }, {}, {})
                .then((response) => {
                    password = response.data.password;
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                });
        },
        updateAddress: function (addr) {
            AXIOS.put("/updateCustomer/" + id + "/" + { password } + "/" + { newAddress }, {}, {})
                .then((response) => {

                    address = response.data.address;
                })
                .catch((e) => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                });
        },
    },
};