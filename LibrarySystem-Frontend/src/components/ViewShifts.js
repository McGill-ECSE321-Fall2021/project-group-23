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
    name: 'viewshifts',
    // takes in the id of librarian
    props: ['librarianIdP'],
    data () {
        return {
            librarian: {},
            currentShifts: [],
            errorLibrarian: '',
            errorShift: '',
            response: []
        }
    },
    // gets librarian that matches id to obtain shifts
    // async ensures response is gotten first
    created: async function() {
        await AXIOS.get('/getLibrarianById/' + this.librarianIdP)
        .then(response => {
            this.librarian = response.data
            this.errorLibrarian = ''
        })
        .catch(e => {
            this.errorLibrarian = e
            console.log(this.errorLibrarian)
        })
        
        var x;
        for (x in this.librarian.weeklySchedule.shifts) {
            AXIOS.get('/getShiftById/' + this.librarian.weeklySchedule.shifts[x].shiftId)
            .then(response => {
                this.currentShifts.push(response.data)
                this.errorShift = ''
            })
            .catch(e => {
                this.errorShift = e
                console.log(this.errorShift)
            })
        }
    }
}