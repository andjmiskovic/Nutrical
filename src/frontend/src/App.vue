<template>
  <v-app>
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/vuetify/2.6.7/vuetify.min.js'>
    <link href="./assets/style.css" rel="stylesheet">
    <HelloWorld />
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import './assets/style.css';

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>
