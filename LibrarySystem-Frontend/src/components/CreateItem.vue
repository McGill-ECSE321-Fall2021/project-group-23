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
      <tr>
          <button @click="deleteItem(selectedItem[0].id)">Delete Item</button>
      </tr>
    </table>
    <p>
        <span v-if="errorItem" style="color:red"> {{errorItem}} </span>
    </p>
    <h2>Delete Item</h2>
    <input
      class="form-control"
      v-model="filters.title.value"
      placeholder="Search By Title"
    />
    <v-table
      :data="items"
      :filters="filters"
      :currentPage.sync="currentPage"
      :pageSize="10"
      @totalPagesChanged="totalPages = $event"
      selectedClass="table-info"
      @selectionChanged="selectedItem = $event"
      class="table-hover"
    >
      <thead slot="head">
        <th>ID</th>
        <th>TITLE</th>
        <th>TYPE</th>
        <th>Status</th>
      </thead>
      <tbody slot="body" >
        <v-tr v-for="item in items" :key="item.id" :row="item">
          <td>{{ item.id }}</td>
          <td>{{ item.title }}</td>
          <td>{{ item.type }}</td>
          <td>{{ item.status }}</td>
        </v-tr>
      </tbody>
    </v-table>
    <smart-pagination
      :currentPage.sync="currentPage"
      :totalPages="totalPages"
    />
  </div>
</template>

<script src="./CreateItem.js">

</script>

<style scoped>
select {
 height: 30px;
}
v-table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
th {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #000;
  color: white;
}
input {
  width: 200px;
}
</style>