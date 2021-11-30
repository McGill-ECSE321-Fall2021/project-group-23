<template>
    <div id="updateheadinfo" class="container-fluid">
        <h2>Update Head Librarian Information</h2>
        <!-- Inputs for changing account info -->
        <table class="centerTable">
            <tr>
                <th>First Name</th>
                <td></td>
                <td> <th>Current First Name</th> </td>
            </tr>
            <tr>
                <td>
                    <input type="text" id="newfName" v-model="newFirst">
                </td>
                <td></td>
                <td>
                    <p>
                        <span>{{currFirstName}} </span>
                    </p>
                </td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td></td>
                <td> <th>Current Last Name</th> </td>
            </tr>
            <tr>
                <td>
                    <input type="text" id="newlName" v-model="newLast">
                </td>
                <td></td>
                <td>
                    <p>
                        <span>{{currLastName}} </span>
                    </p>
                </td>
            </tr>
        </table>
        <button
            v-bind:disabled="!newFirst || !newLast" 
            v-on:click="updateHeadInfo(newFirst, newLast)">Update Account Information
        </button>
        <button v-on:click="returnToPrev()">Return to Previous Page</button>
    </div>
</template>

<script src="./updateHead.js">

</script>

<style>
.centerTable { margin: 0px auto; }
</style>
