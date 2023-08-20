<template>
  <div class="register-photo">
    <div class="form-container">
      <div class="image-holder"></div>
      <form v-on:keyup.enter="login">
        <label style="font-size: 20px; margin: 10px; font-weight: 400">Welcome to</label>
        <img
            class="text-center"
            style="height: 100px; margin-top: -20px"
            src="../assets/logo.png"
            alt=""/>
        <p>
          <InputText v-model="email" style="width: 100%" placeholder="Email"/>
        </p>
        <p>
          <Password v-model="password" style="width: 100%" placeholder="Password" toggleMask :feedback="false"></Password>
        </p>
        <Button
            class="login-button"
            @click="login">Login
        </Button>
        <span v-if="nonExistent" class="already" style="font-size: 15px; color: #dc143c">
          Wrong email or password. Please try again.
        </span>
        <span v-else><br></span>
        <a class="already" style="font-size: 15px"
        >You don't have an account?
          <b @click="changeToRegistration" style="cursor: pointer"
          >Register here.</b
          ></a
        >
      </form>
    </div>
  </div>
</template>
<script>
import AuthService from "@/services/AuthService";

export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: "",
      nonExistent: false
    }
  },
  methods: {
    changeToRegistration() {
      this.$router.push({path: "/registration"});
    },
    async login() {
      if (this.email === "" || this.password === "") {
        this.nonExistent = true;
      } else {
        await AuthService.login({"email": this.email, "password": this.password}).then((loginResponse) => {
          if (loginResponse[0]["accessToken"] === null) {
            this.nonExistent = true;
          } else {
            this.nonExistent = false;
            localStorage.setItem('token', "Bearer " + loginResponse[0]["accessToken"]);
            localStorage.setItem('userRole', loginResponse[0]["role"]);
            this.$router.push('/dashboard');
          }
        }).catch(() => {
          this.nonExistent = true;
        });
      }
    }
  }
}
</script>

<style>
.register-photo {
  padding: 40px 0;
}

.p-inputtext {
  width: 100%;
}

.register-photo .image-holder {
  display: table-cell;
  width: auto;
  background: url(https://images.unsplash.com/photo-1544926206-bd2c1e248045?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80);
  background-size: cover;
}

.register-photo .form-container {
  display: table;
  max-width: 900px;
  width: 90%;
  margin: 0 auto;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 3px 5px 0 rgba(0, 0, 0, 0.19);
}

.login-button {
  width: 100%;
  background-color: var(--green);
  text-align: center;
  border-color: var(--green);
  display: block;
  margin-top: 15px;
}

.register-photo form {
  display: table-cell;
  width: 400px;
  background-color: #ffffff;
  padding: 40px 60px;
  color: #505e6c;
}

@media (max-width: 991px) {
  .register-photo form {
    padding: 40px;
  }
}

.register-photo form .form-control {
  background: transparent;
  border: none;
  border-bottom: 1px solid #dfe7f1;
  border-radius: 0;
  box-shadow: none;
  outline: none;
  color: inherit;
  text-indent: 0;
  height: 40px;
}

.register-photo form .already {
  display: block;
  text-align: center;
  font-size: 12px;
  color: #6f7a85;
  opacity: 0.9;
  text-decoration: none;
  margin-top: 10px;
}
</style>