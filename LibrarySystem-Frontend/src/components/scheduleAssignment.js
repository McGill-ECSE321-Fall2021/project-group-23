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
    name: 'scheduleassignment',
    props: ['id'],
    data () {
        return {
            shifts: [],
            currentShifts: [],
            newShift: {
                day: '',
                startTime: '',
                endTime: '',
                id: ''
            },
            errorShift: '',
            response: []
        }
    },
}
