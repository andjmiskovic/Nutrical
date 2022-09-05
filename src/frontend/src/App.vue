<template>
  <v-app>
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <link href="./assets/style.css" rel="stylesheet">
    <v-main>
      <Dashboard/>
    </v-main>
  </v-app>
</template>

<script>
import Dashboard from './components/Dashboard.vue'

export default {
  name: 'App',

  components: {
    Dashboard,
  },

  data: () => ({
    //
  }),
}
</script>
