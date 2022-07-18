<template>
    <h1>{{msg}}</h1>
    <NavBar></NavBar>
</template>

<script>
import NavBar from './NavBar.vue'
export default {
  name: 'HelloWorld',
  components: { NavBar },
  data() {
    return {
        msg: ''
    }
  },
  mounted() {
    fetch("/api/calculator/hello")
    .then((response) => response.text())
    .then((data) => {
        this.msg = data;
    })
  }
}
</script>