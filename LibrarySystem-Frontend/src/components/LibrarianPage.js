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
        
    }
}
