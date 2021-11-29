<template>
  <div id="CreateReservation">
    <v-table
      :data="items"
      :filters="filters"
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
      <tbody slot="body">
        <v-tr v-for="item in items" :key="item.id" :row="item">
          <td>{{ item.id }}</td>
          <td>{{ item.title }}</td>
          <td>{{ item.type }}</td>
          <td>{{ item.status }}</td>
        </v-tr>
      </tbody>
    </v-table>
  </div>
</template>

<script src="./CreateReservation.js">

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