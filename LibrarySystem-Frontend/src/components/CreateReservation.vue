<template>
  <div id='create-reservation'> 
    <v-table
      :data="items"
      :filters="filters"
      @selectionChanged="selectedItem = $event"
      class="table-hover"
    >
  </div>
</template>
<script>
</script>
<style>
</style>