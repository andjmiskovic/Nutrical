<template>
  <div class="relative">
    <div class="menubar-wrapper">
      <div class="menubar">
        <img alt="logo" src="../assets/logo.png" height="40" class="logo" />
        <div class="menu-buttons">
          <Button label="Clients" icon="pi pi-user" @click="$router.push('/clients')" class="p-button-text" />
          <Button label="Log out" icon="pi pi-sign-out" @click="logout()" class="p-button-text" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  props: ["activePage"],
  name: "NavBar",
  components: {},
  data() {
    return {};
  },
  methods: {
    logout() {
      AuthService.logout().catch(() => {
        this.$router.push('/');
      });
    }
  },
};
</script>

<style>
.relative {
  position: relative;
}

.menubar-wrapper {
  position: sticky;
  top: 0;
  background-color: #ffffff;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* Add shadow */
  z-index: 1000;
}

.menubar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
}

.logo {
  height: 40px;
  margin-right: 10px;
}

.menu-buttons {
  display: flex;
  align-items: center;
}

.p-button-text {
  margin-right: 10px;
}
</style>