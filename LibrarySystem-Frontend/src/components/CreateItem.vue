<template>
  <div id="CreateItem">
    <table>
      <tr>
      <h2>Create Item</h2>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newItem" placeholder="Title">
        </td>
        <td>
          ​<select type="text" v-model="newType">
            ​<option disabled selected value="">Select Type</option>
            <option >Book</option>
            <option >Movie</option>
            <option >Album</option>
            <option >Newspapers</option>
            <option >Archive</option>
          </select>
        </td>
      </tr>
      <tr>
          <button v-bind:disabled="!newType || !newItem" @click="createItem(newItem, newType)">Create Item</button>
      </tr>
    </table>
    <p>
        <span v-if="errorItem" style="color:red"> {{errorItem}} </span>
    </p>
  </div>
</template>

<script src="./CreateItem.js">
</script>

<style scoped>
select {
 height: 30px;
}
</style>