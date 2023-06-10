<template>
  <body class="background-photo">
    <Card style="width: 50%; margin: 150px auto auto;">
      <template #title><i class='bx bxs-hot'></i>You have successfully verified your email</template>
      <template #content>
        <Button @click="changeToLogin" class="prime-btn"
        >Login
        </Button
        >
      </template>
    </Card>
  </body>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  name: "Verification",
  mounted() {
    const verificationCode = this.$route.query.code || "";
    AuthService.verify(verificationCode).then((res) => {
      console.log(res);
    });
  },
  data() {
    return {
    };
  }, methods: {
    changeToLogin() {
      this.$router.push({path: "/"});
    },
  }
};
</script>

<style scoped>
.background-photo {
  background-size: cover;
  padding: 0 !important;
  background-image: url(https://images.unsplash.com/photo-1483794344563-d27a8d18014e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80);
}
</style>