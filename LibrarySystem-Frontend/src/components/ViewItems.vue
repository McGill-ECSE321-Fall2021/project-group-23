<template>
  <div id="ViewItems">
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
      class="table-hover"
      id="itemm"
    >
      <thead slot="head">
        <th>ID</th>
        <th>TITLE</th>
        <th>TYPE</th>
        <th>Status</th>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <v-tr v-for="item in displayData" :key="item.id" :row="item">
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

<script src="./ViewItems.js">
</script>

<style scoped>
v-table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
}
td,
th {
  border: 1px solid #ddd;
  padding: 8px;
  width: 1%;
}
th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #000;
  color: white;
}
input {
  width: 100%;
}
#itemm tr:nth-child(even){background-color: #f2f2f2;}
</style>