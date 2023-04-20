<template>
  <div style="display: flex; margin-top: 25px; margin-left: 30px">
    <img src="../assets/logo.png" style="height: 35px" alt="logo">
  </div>
  <div style="display: flex; justify-content: center; align-items: center; height: 80%; position: relative">
    <div>
      <h2>Login</h2>
      <p>Welcome back to Nutrical!</p>
      <span class="p-input-icon-left" style="margin-bottom: 10px; width: 100%">
            <i class="pi pi-user"/>
            <InputText type="text" v-model="email" placeholder="Email"/>
      </span>
      <br>
      <span class="p-input-icon-left" style="margin-bottom: 10px; width: 100%">
            <i class="pi pi-lock"/>
            <InputText type="text" v-model="password" placeholder="Password"/>
      </span>
      <br>
      <Button style="width: 100%; text-align: center" @click="login">Login</Button>
    </div>
  </div>
  <div>
    <p style="width: 100%; float: right; margin-right: 10px">
      Don't have an account?
<!--      <router-link :to="{name: 'Registration'}"><b>Create one</b></router-link>-->
    </p>
  </div>
</template>

<script>
import AuthService from "@/services/AuthService.js";

export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: ""
    }
  },
  methods: {
    login() {
      console.log("EMAIL: ", this.email)
      console.log("PASSWORD: ", this.password)
      AuthService.login(this.email, this.password)
          .then((loginResponse) => {
            console.log(loginResponse)
            localStorage.setItem('token', "Bearer " + loginResponse["accessToken"]);
            localStorage.setItem('userRole', loginResponse["role"]);
            this.$router.push({name: 'Dashboard'});
          })
          .catch(error => {
            console.log(error);
          });
    }
  }
}
</script>

<style>

</style>