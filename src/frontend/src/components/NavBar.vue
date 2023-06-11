<template>
  <div class="sidebar">
    <img src="../assets/icon_white.png" class="logo" style="margin-top: 10px" alt="">
    <hr style="height: 1px; background: var(--blue)">
    <ul>
      <li>
        <a href="#" @click="$router.push('/dashboard')" :class="{ currentPage: activePage === 'dashboard' }">
          <i class="pi pi-microsoft"></i>
        </a>
        <span class="tooltip">Dashboard</span>
      </li>
      <li>
        <a href="#" @click="$router.push('/clients')" :class="{ currentPage: activePage === 'clients' }">
          <i class="pi pi-user"></i>
        </a>
        <span class="tooltip">Clients</span>
      </li>
      <li>
        <a href="#" :class="{ currentPage: activePage === 'settings' }">
          <i class="pi pi-check"></i>
        </a>
        <span class="tooltip">Settings</span>
      </li>
      <li>
        <a href="#" @click="logout()">
          <i class="pi pi-sign-out"></i>
        </a>
        <span class="tooltip">Log out</span>
      </li>
    </ul>
  </div>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  props: ["activePage"],
  name: "NavBar",
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
.logo {
  max-height: 50px;
}

.sidebar {
  height: 100%;
  width: 78px;
  background: var(--dark);
  top: 0;
  left: 0;
  position: fixed;
  padding: 6px 14px;
  z-index: 98;
}

.sidebar ul {
  margin-top: 10px;
  margin-left: -45px;
}

.sidebar ul li {
  height: 50px;
  margin: 0 5px;
  list-style: none;
  position: relative;
  width: 100%;
  line-height: 50px;
}

.sidebar ul li a {
  color: var(--white);
  display: flex;
  align-items: center;
  border-radius: 12px;
  text-decoration: none;
  transition: all 0.4s ease;
  white-space: nowrap;
}

.sidebar ul li .tooltip {
  position: absolute;
  height: 35px;
  width: 122px;
  background: var(--white);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  left: 122px;
  top: 0;
  transform: translate(-50%, -50%);
  border-radius: 6px;
  line-height: 35px;
  text-align: center;
  transition: 0s;
  opacity: 0;
  pointer-events: none;
  display: block;
}

.sidebar ul li:hover .tooltip {
  transition: all 0.5s ease;
  opacity: 1;
  top: 50%;
}

.currentPage {
  background-color: var(--gray);
}

.sidebar ul li a:hover {
  cursor: pointer;
}

.sidebar ul li i {
  height: 50px;
  color: var(--green);
  min-width: 60px;
  border-radius: var(--border-radius);
  text-align: center;
  line-height: 50px;
}
</style>