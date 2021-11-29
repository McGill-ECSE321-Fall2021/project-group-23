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
    name: 'updateheadinfo',
    data () {
        return {
            librarian: {},
            currFirstName: '',
            currLastName: '',
            newFirst: '',
            newLast: '',
            newPassword: '',
            errorLibrarian: ''
        }
    },
    created: function() {
        AXIOS.get('/getLibrarianById/' + 1234)
        .then(response => {
            this.librarian = response.data
            this.currFirstName = this.librarian.firstName;
            this.currLastName = this.librarian.lastName;
        })
        .catch(e => {
            this.errorLibrarian = e
        })
    },
    methods: {
        returnToPrev: function() {
            this.$router.push({ path: `/LibrarianManagement/`})
        },
        updateHeadInfo: function(newFName, newLName, newPass) {
            AXIOS.put('/updateHeadLibrarian/1234/'+ newFName + '/' + newLName + '/' + newPass + '/' + this.librarian.weeklySchedule.weeklyScheduleId)
            .then(response => {
                this.librarian = response.data
                this.currFirstName = this.librarian.firstName
                this.currLastName = this.librarian.lastName;
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorLibrarian = errorMsg
            })
        }
    }
}