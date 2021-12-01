<template>
    <div id="hire/fire-librarian" class="container-fluid">
        <h1 style="text-align:center">Librarian Management</h1>
        <div>
            <button class="btn btn-light" v-on:click="customerManagement(selectedCustomer[0])">Customer Access</button>
            <button class="btn btn-light" v-on:click="viewShifts()">View Shifts</button>
            <button class="btn btn-light" v-on:click="createItem()">Item Management</button>
            <button class="btn btn-light" v-on:click="goToProfile()">Profile</button>
        </div>
        
        <hr>
        <h2 style="text-align:center">Customers</h2>
        <button style="margin-bottom: 6px;" class="btn btn-light" v-on:click="updateValues(selectedCustomer[0])">Update Balance and Statuses</button>
        <div id="customercontrol1" style="text-align:center">
                <input type="number" min="0" placeholder="New Balance" v-model="newBalance">
        </div>
        <div id="customercontrol2">
                <input type="checkbox" v-model="verificationVal">
                <span> Verified? </span>
                <input type="checkbox" v-model="localVal">
                <span> Local? </span>
        </div>
        <p>
            <span v-if="errorCustomer" style="text-align:center; color:red"> {{errorCustomer}} </span>
        </p>
        <v-table :data="customers" selectedClass="table-info" @selectionChanged="selectedCustomer = $event" class="table-hover centerTable">
            <thead slot="head">
                <th style="padding: 20px">First Name</th>
                <th style="padding: 20px">Last Name</th>
                <th style="padding: 20px">Address</th>
                <th style="padding: 20px">Email</th>
                <th style="padding: 20px">Balance</th>
                <th style="padding: 20px">Verification Status</th>
                <th style="padding: 20px">Local Status</th>
                <th style="padding: 20px">Customer ID</th>
            </thead>
            <tbody slot="body">
                <v-tr v-for="customer in customers" :key=customer.customerId :row=customer.customerId>
                    <td> {{customer.firstName}} </td>
                    <td> {{customer.lastName}} </td>
                    <td> {{customer.address}} </td>
                    <td> {{customer.email}} </td>
                    <td> {{customer.accountBalance}} </td>
                    <td> {{customer.isVerified}} </td>
                    <td> {{customer.isLocal}} </td>
                    <td> {{customer.customerId}} </td>
                </v-tr>
            </tbody>
        </v-table>
    </div>    
</template>

<script src="./LibrarianPage.js"></script>

<style>

</style>