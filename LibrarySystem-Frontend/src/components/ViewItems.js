
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'ViewItems',

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
            // selectedItem
            filters: {
                title: { value: '', keys: ['title'] }
            },
            currentPage: 1,
            totalPages: 0
        }
    },

    methods: {
        typeFilter(filterValue, item) {
            return (item.type != "Archive");
        }
    }
}