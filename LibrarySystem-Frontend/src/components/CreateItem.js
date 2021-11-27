import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'CreateItem',

    created: function () {
        AXIOS.get('/getAllItems')
            .then(response => {
                // JSON responses are automatically parsed.
                this.items = response.data
            })
            .catch(e => {
                this.errorItem = e
            })
    },

    data() {
        return {
            items: [],
            errorItem: '',
            newItem: '',
            newType: ''
        }
    },

    methods: {

        createItem: function (title, type) {
            AXIOS.post('/createItem/' + title + '/' + type).then(response => {
                this.items.push(response.data)
                this.errorItem = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorItem = errorMsg
            })

        },
    }
}