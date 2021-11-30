<template>
    <div id="hire/fire-librarian" class="container-fluid">
        <h1 style="text-align:left">Librarian Management</h1>
        <div>
            <button class="btn btn-light" v-on:click="customerManagement(selectedCustomer[0])">Customer Management</button>
            <button class="btn btn-light" v-on:click="viewShifts()">View Shifts</button>
            <button class="btn btn-light" v-on:click="createItem()">Item Management</button>
            <button class="btn btn-light" v-on:click="goToProfile()">Profile</button>
        </div>
        
        <hr>
        <v-table :data="customers" selectedClass="table-info" @selectionChanged="selectedCustomer = $event" class="table-hover">
            <thead slot="head">
                <th style="padding: 20px">First Name</th>
                <th style="padding: 20px">Last Name</th>
                <th style="padding: 20px">Address</th>
                <th style="padding: 20px">Email</th>
                <th style="padding: 20px">Customer ID</th>
            </thead>
            <tbody slot="body">
                <v-tr v-for="customer in customers" :key=customer.customerId :row=customer.customerId>
                    <td> {{customer.firstName}} </td>
                    <td> {{customer.lastName}} </td>
                    <td> {{customer.address}} </td>
                    <td> {{customer.email}} </td>
                    <td> {{customer.customerId}} </td>
                </v-tr>
            </tbody>
        </v-table>
    </div>    
</template>

<script src="./LibrarianPage.js"></script>

<style>

</style>