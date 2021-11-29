<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Login/Signup</h2>
    <ul>
      <li><a href="https://vuejs.org" target="_blank">Login</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank">Signup</a></li>
    </ul>
    <h2>About us</h2>
      <li>{{ email }}</li>
      <li>{{ adress }}</li>
      <li>{{ number }}</li>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Library',
      email: 'Email: group13.mail.mcgill.ca',
      adress: 'Adress: 13 McGill avenue',
      number: 'Call: 514-100-1313'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
