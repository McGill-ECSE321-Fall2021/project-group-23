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
            errorItem: '',
            newItem: '',
            newType: '',
            items: [],
            selectedItem: [],
            filters: {
                title: { value: '', keys: ['title'] }
            },
            currentPage: 1,
            totalPages: 0
        }
    },

    methods: {
        getAllItems: function () {
            AXIOS.get('/getAllItems')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.items = response.data
                })
                .catch(e => {
                    this.errorItem = "Error: failed retrieving items from database"
                })
        },
        createItem: function (title, type) {
            AXIOS.post('/createItem/' + title + '/' + type).then(response => {
                this.getAllItems()
                this.errorItem = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorItem = "Error: failed creating the item"
            })

        },
        deleteItem: function (id) {
            console.log(id)
            AXIOS.delete('/deleteItem/' + id).then(response => {
                this.getAllItems()
                this.errorItem = ''
            }).catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorItem = "Error: failed deleting the Item"
            })

        },
    }
}