<template>
  <body>
  <div class="container">
    <div class="signin-signup">
      <form action="" class="sign-in-form">
        <h2 class="title">Sign in</h2>
        <div class="input-field">
          <i class="fas fa-user"></i>
          <input type="text" placeholder="Username">
        </div>
        <div class="input-field">
          <i class="fas fa-lock"></i>
          <input type="password" placeholder="Password">
        </div>
        <Button
            class="p-button-rounded p-button-primary"
        >Login
        </Button>
      </form>
    </div>
    <div class="panels-container"></div>
  </div>

  </body>
</template>
<script>
export default {
  name: "HomePage",
  components: {}
}
</script>
<style>

</style>