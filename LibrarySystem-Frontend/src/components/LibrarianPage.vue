<template>
    <div id="hire/fire-librarian" class="container-fluid">
        <h1 style="text-align:left">Head Librarian Management</h1>
        <div>
            <button class="btn btn-light" v-on:click="customerManagement()">Customer management</button>
            <button class="btn btn-light" v-on:click="viewShifts()">View Shifts</button>
            <button class="btn btn-light" v-on:click="createItem()">Item Management</button>
            <button class="btn btn-light" v-on:click="goToProfile()">Profile</button>
        </div>

        <hr>
    </div>    
</template>

<script src="./LibrarianPage.js"></script>

<style>

</style>