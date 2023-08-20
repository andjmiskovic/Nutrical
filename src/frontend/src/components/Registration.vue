<template>
  <div class="register-photo">
    <div class="form-container" id="register-form-container">
      <form>
        <label style="font-size: 20px; font-weight: 500"
        >Registrate to Nutrical</label
        >

        <!--FIRST TAB-->
        <div class="tab">
          <label style="font-size: 14px; margin-bottom: 20px">Enter your login credentials.</label>
          <div style="height: 70px">
            <InputText id="email" v-model="email" style="width: 100%" placeholder="Email address" required/>
          </div>
          <div style="height: 70px">
            <InputText id="username" v-model="username" style="width: 100%" placeholder="Username"/>
          </div>
          <div style="height: 70px">
            <Password v-model="password" placeholder="Password" id="password" style="width: 100%" toggleMask
                      :feedback="false" required></Password>
          </div>
          <div style="height: 70px">
            <Password v-model="password2" placeholder="Repeat password" id="password2" style="width: 100%" toggleMask
                      :feedback="false" required></Password>
          </div>
        </div>

        <!--SECOND TAB-->
        <div class="tab">
          <label style="font-size: 14px; margin-bottom: 20px">Enter your primary data.</label>
          <div style="height: 70px">
            <InputText id="name" v-model="name" style="width: 100%" placeholder="Name" required/>
          </div>
          <div style="height: 70px">
            <InputText id="surname" v-model="surname" style="width: 100%" placeholder="Surname" required/>
          </div>
          <div style="height: 70px">
            <InputText id="phoneNumber" v-model="phoneNumber" style="width: 100%" placeholder="Phone number" required/>
          </div>
          <div style="height: 70px">
            <InputText id="licence" v-model="licence" style="width: 100%" placeholder="Nutritionist licence number"/>
          </div>
        </div>

        <div style="overflow: auto; margin-top: 20px">
          <div style="float: right">
            <Button v-if="!emailSending" id="prevBtn" style="border: none" @click="nextPrev(-1)"
            >Previous
            </Button
            >
            <Button
                v-if="currentTab === 0"
                id="nextBtn"
                class="prime-btn"
                @click="nextPrev(1)"
            >Next
            </Button
            >
            <Button v-if="currentTab === 1 && !emailSending" @click="register" style="margin-left: 5px"
                    class="prime-btn"
            >Submit
            </Button
            >
          </div>
        </div>
        <ProgressSpinner
            v-if="emailSending"
            class="spinning mr-5"
        />
        <Dialog modal id="success" header="Header" :style="{ width: '50vw' }">
          <div class="d-block text-center">
            <h4>
              Your registration is almost done. Check out your email to
              verify your account.
            </h4>
          </div>
        </Dialog>
        <div style="text-align: center; margin-top: 20px">
          <span class="step"></span>
          <span class="step"></span>
        </div>
        <div style="text-align: center; margin-top: 20px; font-size: 13px">
          <span @click="changeToLogin()" style="cursor: pointer">Back to login</span>
        </div>
      </form>
      <div class="image-holder"></div>
    </div>
  </div>
  <Toast />
</template>

<script>
import AuthService from "@/services/AuthService";
// import { useToast } from "primevue/usetoast";
// import Toast from 'primevue/toast';
//
// const toast = useToast();

export default {
  name: "Registration",
  components: {
    // Toast
  },
  mounted() {
    this.showTab(0);
  },
  data() {
    return {
      currentTab: 0,
      email: "",
      username: "",
      password: "",
      name: "",
      surname: "",
      phoneNumber: "",
      licence: "",
      password2: "",
      emailSending: false,
    };
  },
  methods: {
    showTab(n) {
      const x = document.getElementsByClassName("tab");
      x[n].style.display = "block";
      if (n === 0) document.getElementById("prevBtn").style.display = "none";
      else document.getElementById("prevBtn").style.display = "inline";
      this.fixStepIndicator(n);
    },
    nextPrev(n) {
      const x = document.getElementsByClassName("tab");
      x[this.currentTab].style.display = "none";
      this.currentTab = this.currentTab + n;
      if (this.currentTab >= x.length) {
        return false;
      }
      this.showTab(this.currentTab);
    },
    fixStepIndicator(n) {
      const x = document.getElementsByClassName("step");
      for (let i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
      }
      x[n].className += " active";
      document.getElementsByClassName("step")[this.currentTab].className +=
          " finish";
    },
    getFormData() {
      const formData = new FormData();
      formData.append("email", this.email);
      formData.append("username", this.username);
      formData.append("password", this.password);
      formData.append("firstName", this.name);
      formData.append("lastName", this.surname);
      formData.append("phoneNumber", this.phoneNumber);
      formData.append("licence", this.licence);
      return formData;
    },
    formValid() {
      const requiredFields = [
        "email",
        "password",
        "password2",
        "name",
        "surname",
        "phoneNumber",
      ];

      for (const field of requiredFields) {
        if (!this[field]) {
          return false;
        }
      }

      if (!this.validateEmail(this.email)) {
        this.emailError = "Invalid email format.";
        return false;
      }
      return this.password === this.password2;
    },
    validateEmail(email) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(email);
    },
    async register() {
      if (!this.formValid()) {
        // toast.add({severity: 'warn', summary: 'Invalid form', detail: 'Check your input', life: 3000});
      } else {
        this.emailSending = true;
        await AuthService.register(this.getFormData()).then(() => {
          // toast.add({
          //   severity: 'success',
          //   summary: 'Email sent!',
          //   detail: 'Check your email to verify account',
          //   life: 3000
          // });
          this.emailSending = false;
        }).catch(() => {
          // toast.add({severity: 'warn', summary: 'Error', detail: 'We could not send an email :(', life: 3000});
        });
      }
    },
    changeToLogin() {
      this.$router.push({path: "/"});
    }
  },
};
</script>

<style>
.tab {
  display: none;
}

#prevBtn {
  background-color: #bbbbbb;
}

.step {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbbbbb;
  border: none;
  border-radius: 50%;
  display: inline-block;
  opacity: 0.5;
}

.step.active {
  opacity: 1;
}

.step.finish {
  background-color: #04aa6d;
}
</style>