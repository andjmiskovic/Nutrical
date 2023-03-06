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
      <Button style="width: 100%; text-align: center">Login</Button>
    </div>
  </div>
  <div>
    <p style="width: 100%; float: right; margin-right: 10px">
      Don't have an account?
      <span><b>Create one</b></span>
    </p>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: ""
    }
  }
}
</script>

<style>

</style>