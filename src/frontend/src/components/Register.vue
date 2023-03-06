<template>
  <Card>
    <template #title> Login</template>
    <template #content>
      <p>Login</p>
      <span class="p-input-icon-left">
            <i class="pi pi-user"/>
            <InputText type="text" v-model="email" placeholder="Email"/>
        </span>
      <span class="p-input-icon-left">
            <i class="pi pi-lock"/>
            <InputText type="text" v-model="password" placeholder="Password"/>
        </span>
    </template>
  </Card>
</template>

<script>
export default {
  name: "Register",
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