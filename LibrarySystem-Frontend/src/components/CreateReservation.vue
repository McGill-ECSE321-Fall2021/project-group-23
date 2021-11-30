<template>
  <div id="CreateReservation">
    <v-table
      :data="reservations" 
      :filters="filters"
      :currentPage.sync="currentPage"
      :pageSize="10"
      @totalPagesChanged="totalPages = $event"
      selectedClass="table-info"
      @selectionChanged="selectedReservation = $event"
      class="table-hover"
      id="ress"
    >
      <thead slot="head">
        <th>RESERVATION ID</th>
        <th>ITEM TITLE</th>
        <th>RESERVATION START DATE</th>
        <th>RESERVATION END DATE</th>
      </thead>
      <tbody slot="body">
        <v-tr v-for="reservation in reservations" :key="reservation.id" :row="reservation">
          <td>{{ reservation.id }}</td>
          <td>{{ reservation.item.title }}</td>
          <td>{{ reservation.startDate }}</td>
          <td>{{ reservation.endDate }}</td>
        </v-tr>
      </tbody>
    </v-table>

    <v-table
      :data="items"
      :filters="filters"
      :currentPage.sync="currentPage"
      :pageSize="10"
      @totalPagesChanged="totalPages = $event"
      selectedClass="table-info"
      @selectionChanged="selectedItem = $event"
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
    <button
      type="button"
      @click="createReservation(customerId, selectedItem[0].id, false, date)"
    >
      Create Reservation
    </button>
    <button
      type="button"
      @click="deleteReservation(selectedReservation[0].id)"
    >
      Delete Reservation
    </button>
    <span v-if="errorReservation" style="color: red">
      {{ errorReservation }}
    </span>
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
td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  border: 1px solid #ddd;
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #000;
  color: white;
}
input {
  width: 200px;
}
#itemm tr:nth-child(even){background-color: #f2f2f2;}
#ress tr:nth-child(even){background-color: #f2f2f2;}
</style>