import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
    }
};

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = backendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    props: ["librarianIdP"],
    name: 'librarianPageManagement',
    data () {
        return {
           librarian: {},
           errorLibrarian: '',
           selectedCustomer: {},
           customers: [], 
           errorCustomer: '',
           id: this.librarianIdP,
           response: []
        }
    },
    created: function () {
        // Initialize librarians from backend
        AXIOS.get('/getAllCustomers')
        .then(response => {
            this.customers = response.data
        })
        .catch(e => {
            this.errorCustomer = e
        })
       
    },
    methods: {
        goToProfile: async function() {
            await AXIOS.get('/getLibrarianById/' + this.id)
            .then(response => {
                this.librarian = response.data
            })
            .catch(e => {
                this.errorLibrarian = e
            }) 
            this.$router.push({ path: `/Profile/` + this.librarian.firstName + '/' + this.librarian.lastName + '/' + this.id+ '/' + this.librarian.address + '/' + this.librarian.email + '/' + this.librarian.password + '/' + 'Librarian'})
        },
        createItem: function() {
            this.$router.push({ path: `/CreateItem` })
        },
        viewShifts: function() {
            this.$router.push({ path: `/ViewShifts/` + this.id})
        },
        customerManagement: async function(customerId) {
            if (customerId == undefined) {
                this.errorCustomer = 'Please select a customer to manage first.'
            }
            else {
                await AXIOS.get('getCustomerById/' + customerId)
                .then(response => {
                    this.selectedCustomer = response.data
                })
                .catch(e => {
                    this.errorCustomer = e
                })
                this.errorCustomer = ''
                this.$router.push({ path: `/CustomerHomePage/${this.selectedCustomer.firstName}/${this.selectedCustomer.lastName}/${this.selectedCustomer.customerId}/${this.selectedCustomer.address}/${this.selectedCustomer.email}/${this.selectedCustomer.password}/Customer` })
            }
        }
        
    }
}
