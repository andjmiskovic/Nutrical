<template>
  <div class="sidebar" :class="{ active: isActive }">
    <div class="logo_content">
      <i class="bx bx-menu" @click="toggle" id="menu-btn"></i>
      <img class="logo" src="../assets/logo.png" />
    </div>
    <ul>
      <li>
        <i class="bx bx-search"></i>
        <input type="text" placeholder="Search..." />
      </li>
      <li>
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="links_name">Dashboard</span>
        </a>
        <span class="tooltip">Dashboard</span>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-user"></i>
          <span class="links_name">My Profile</span>
        </a>
        <span class="tooltip">My Profile</span>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="links_name">Analytics</span>
        </a>
        <span class="tooltip">Analytics</span>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-cog"></i>
          <span class="links_name">Settings</span>
        </a>
        <span class="tooltip">Settings</span>
      </li>
    </ul>
    <div class="profile_content">
      <div class="profile">
        <div class="profile_details">
          <img src="../assets/images/profile.jpg" alt="" />
          <div class="name">Andjela Miskovic</div>
        </div>
        <i class="bx bx-log-out" id="log_out"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {
      isActive: false,
    };
  },
  methods: {
    toggle() {
      this.isActive = !this.isActive;
    },
  },
};
</script>

<style>
.sidebar {
  height: 100%;
  width: 78px;
  background: var(--dark);
  top: 0;
  left: 0;
  position: fixed;
  padding: 6px 14px;
}
.sidebar.active {
  width: 240px;
}
.sidebar .logo_content .logo {
  display: flex;
  height: 100%;
  width: 150px;
  align-items: center;
  opacity: 0;
  pointer-events: none;
  margin-left: 50px;
}
.sidebar.active .logo_content .logo {
  opacity: 1;
}
.sidebar #menu-btn {
  position: absolute;
  color: var(--white);
  cursor: pointer;
  top: 6px;
  font-size: 20px;
  width: 50px;
  text-align: center;
  line-height: 50px;
  height: 50px;
}
.sidebar ul {
  margin-top: 20px;
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
.sidebar.active ul li .tooltip {
  display: none;
}
.sidebar.active .links_name {
  opacity: 1;
  pointer-events: auto;
}
.sidebar .links_name {
  opacity: 0;
  pointer-events: none;
}
.sidebar ul li:hover .tooltip {
  transition: all 0.5s ease;
  opacity: 1;
  top: 50%;
}
.sidebar ul li input {
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 100%;
  border: none;
  outline: none;
  border-radius: var(--border-radius);
  padding-left: 50px;
  font-size: 0.9rem;
  color: var(--white);
  background: var(--gray);
}
.sidebar ul li .bx-search {
  position: absolute;
  z-index: 99;
  color: var(--white);
  transition: all 0.5s ease;
}
.sidebar ul li .bx-search:hover {
  background: var(--white);
  color: var(--gray);
}
.sidebar ul li a:hover {
  background: var(--gray);
}
.sidebar ul li i {
  height: 50px;
  min-width: 50px;
  border-radius: var(--border-radius);
  text-align: center;
  line-height: 50px;
}
.sidebar .profile_content {
  position: absolute;
  color: var(--white);
  left: 0;
  bottom: 0;
  width: 100%;
}
.sidebar .profile_content .profile {
  position: relative;
  padding: 10px 6px;
  background: var(--gray);
  height: 60px;
}
.sidebar .profile_content .profile_details {
  display: flex;
  align-items: center;
  font-size: 15px;
  opacity: 0;
  pointer-events: none;
  white-space: nowrap;
}
.sidebar .profile_content .profile_details img {
  height: 45px;
  width: 45px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 10px;
}
.sidebar.active .profile_content .profile_details {
  opacity: 1;
  pointer-events: auto;
}
.profile #log_out {
  position: absolute;
  left: 50%;
  bottom: 5px;
  transform: translateX(-50%);
  min-width: 50px;
  line-height: 50px;
  font-size: 20px;
  float: right;
  text-align: center;
}
.sidebar.active .profile #log_out {
  left: 90%;
}
</style>