<template>
  <div id="app">
    <!--!<img src="./assets/logo.png">-->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
table{
  border-color: black;
}
body {
  background-color:lightsteelblue;
}

input {
  border-radius: 3px;
  border: none;
}
.centerTable { margin: 0px auto; }

td {
  border-style: hidden!important;
}
th {
  background-color: black;
  border-style: hidden!important;
  color: white;
}

.center-block {display: block; margin-right:auto; margin-left: auto;
}

</style>
