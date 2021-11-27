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

var backendUrl = backendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl
})

export default {
    name: 'librarianmanagement',
    data () {
        return {
            librarians: [],
            newLibrarian: '',
            selectedLibrarian: '',
            errorLibrarian: '',
            response: []
        }
    },
    created: function () {
        // Initialize librarians from backend
        AXIOS.get('/getAllLibrarians')
        .then(response => {
            this.librarians = response.data
        })
    },
    methods: {
        createLibrarian: function (librarianFName, librarianLName, librarianPass) {
            AXIOS.post('/create')
        }
    }
}