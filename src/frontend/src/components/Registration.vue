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
            <input
                type="email"
                placeholder="Email address"
                class="form-control"
                id="email"
                v-model="email"
            />
          </div>
          <div style="height: 70px">
            <input
                type="text"
                placeholder="Username"
                class="form-control"
                id="username"
                v-model="username"
            />
          </div>
          <div style="height: 70px">
            <input
                type="password"
                placeholder="Password"
                class="form-control"
                id="password"
                v-model="password"
            />
          </div>
          <div style="height: 70px">
            <input
                type="password"
                placeholder="Repeat password"
                class="form-control"
                id="password2"
                v-model="password2"
            />
          </div>
        </div>

        <!--SECOND TAB-->
        <div class="tab">
          <label style="font-size: 14px; margin-bottom: 20px">Enter your primary data.</label>
          <div style="height: 70px">
            <input
                type="text"
                placeholder="Name"
                class="form-control"
                v-model="name"
            />
          </div>
          <div style="height: 70px">
            <input
                type="text"
                placeholder="Surname"
                class="form-control"
                id="surname"
                v-model="surname"
            />
          </div>
          <div style="height: 70px">
            <input
                type="text"
                placeholder="Phone number"
                class="form-control"
                id="phoneNumber"
                v-model="phoneNumber"
            />
          </div>
          <div style="height: 70px">
            <input
                type="text"
                placeholder="Nutritionist licence number"
                class="form-control"
                id="licence"
                v-model="licence"
            />
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
            <Button v-if="currentTab === 1 && !emailSending" @click="register" style="margin-left: 5px" class="prime-btn"
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
      </form>
      <div class="image-holder"></div>
    </div>
  </div>
</template>

<script>
import {
  required,
  minLength,
  email,
  maxLength,
  sameAs,
} from "vuelidate/lib/validators";
import AuthService from "@/services/AuthService";

export default {
  name: "Registration",
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
      emailClicked: false,
      usernameClicked: false,
      passwordClicked: false,
      nameClicked: false,
      surnameClicked: false,
      phoneNumberClicked: false,
      countryClicked: false,
      cityClicked: false,
      typeClicked: false,
      descriptionClicked: false,
      password2Clicked: false,
      emailSending: false,
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
    },
    email: {
      required,
      email,
      minLength: minLength(2),
    },
    username: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
    },
    password: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
    },
    surname: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
    },
    phoneNumber: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
    },
    password2: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20),
      sameAsPassword: sameAs("password"),
    },
    type: {
      required,
    },
  },
  methods: {
    showTab(n) {
      const x = document.getElementsByClassName("tab");
      x[n].style.display = "block";
      if (n === 0) document.getElementById("prevBtn").style.display = "none";
      else document.getElementById("prevBtn").style.display = "inline";
      this.fixStepIndicator(n);
    },
    validateForm() {
      return true;
    },
    nextPrev(n) {
      const x = document.getElementsByClassName("tab");
      if (n === 1 && !this.validateForm()) return false;
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
    async register() {
      this.emailSending = true;
      await AuthService.register(this.getFormData()).then((registerResponse) => {
        this.$bvModal.show("success");
        this.emailSending = false;
      }).catch((err) => {
        console.log(err);
      });
    },
    changeToLogin() {
      this.$router.push({path: "/login"});
    },
    isEmpty(str) {
      return (!str || str.length === 0);
    },
  },
};
</script>

<style>
.ok-border {
  border-color: #16c79a;
}

#regForm {
  background-color: #ffffff;
  margin: 100px auto;
  padding: 40px;
  width: 70%;
  min-width: 300px;
}

/* Hide all steps by default: */
.tab {
  display: none;
}

#prevBtn {
  background-color: #bbbbbb;
}

/* Make circles that indicate the steps of the form: */
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