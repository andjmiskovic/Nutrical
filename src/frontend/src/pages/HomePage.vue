<template>
  <body class="homepage-body">
    <Card class="loginCard">
      <template #content>
        <div class="row">
          <div class="col-5">
            <Login></Login>
          </div>
          <div class="col-7">
            <img alt="" src="../assets/images/recipes.jpg" style="height: 100%; object-fit: cover">
          </div>
        </div>
      </template>
    </Card>
  </body>
</template>

<script>
import Login from "@/components/Login";

export default {
  name: "HomePage",
  components: {Login},
  data() {
    return {
      loginActive: true,
    };
  },
  methods: {
    toggle() {
      this.loginActive = !this.loginActive;
    },
  },
};
</script>

<style>
h2 {
  font-size: 2.8rem !important;
  font-weight: 700;
}

.homepage-body {
  background-color: var(--white);
  justify-content: center;
  align-items: center;
}

img {
  display: block;
  max-width: 100%;
}

.p-card .p-card-body,
.p-card .p-card-body .p-card-content {
  padding: 0;
  position: relative;
  height: 100%;
}

.row {
  position: relative;
  height: 100%
}

a,
button {
  cursor: pointer;
}

.feature img {
  width: 100%;
  max-width: 480px;
}

@media (max-width: 575px) {
  #banner h1 {
    font-size: 3.6rem;
  }
}

input {
  width: 100%;
  padding: 5px;
  border-radius: 4px;
  margin: 5px 0;
  display: inline-block;
  font-size: 12px;
}

.loginCard {
  background: #fff;
  overflow: hidden;
  border-radius: 5px;
  box-shadow: 1px 1px 2px gray;
  height: 70%;
  width: 80%;
  position: absolute;
}
</style>