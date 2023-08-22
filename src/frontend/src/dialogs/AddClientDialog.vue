<template>
  <Dialog v-model:visible="visible" :modal="true" :style="{ width: '500px' }" :header="dialogHeader">
    <form @submit.prevent="addClient">
      <div class="p-fluid">
        <div class="p-field">
          <label for="name">Name</label>
          <InputText id="name" v-model="newClient.name" required/>
        </div>
        <div class="p-field">
          <label for="lastName">Last Name</label>
          <InputText id="lastName" v-model="newClient.lastName" required/>
        </div>
        <div class="p-field">
          <label for="email">Email</label>
          <InputText id="email" v-model="newClient.email" required/>
        </div>
        <div class="p-field">
          <label for="biologicalStatus">Biological Status</label>
          <Dropdown id="biologicalStatus" v-model="newClient.biologicalStatus" :options="healthStatusOptions" required/>
        </div>
        <div class="p-field">
          <label for="activityStatus">Activity Status</label>
          <Dropdown id="activityStatus" v-model="newClient.activityStatus" :options="activityStatusOptions"
                    optionLabel="value" class="p-field"/>
        </div>
        <div class="p-field">
          <label for="weight">Weight (kg)</label>
          <InputNumber id="weight" v-model="newClient.weight" required :mode="'decimal'" :min="0" :max="999"/>
        </div>
        <div class="p-field">
          <label for="height">Height (cm)</label>
          <InputNumber id="height" v-model="newClient.height" required :mode="'decimal'" :min="0" :max="999"/>
        </div>
        <div class="p-field">
          <label for="dateOfBirth">Date of Birth</label>
          <Calendar id="dateOfBirth" v-model="newClient.dateOfBirth" required/>
        </div>
      </div>

      <Button type="submit" style="width: 100%" label="Add" :disabled="!isFormValid"/>
    </form>
  </Dialog>
</template>

<script>

import ClientsService from "@/services/ClientsService";

export default {
  name: "AddClientDialog",
  data() {
    return {
      visible: false,
      dialogHeader: "Add Client",
      newClient: {
        name: "",
        lastName: "",
        email: "",
        biologicalStatus: null,
        activityStatus: null,
        weight: null,
        height: null,
        dateOfBirth: null,
      },
      healthStatusOptions: ["Man", "Woman", "Pregnant", "Breastfeeding"],
      activityStatusOptions: [
        {label: "Sedentary", value: "Little or no exercise, desk job"},
        {label: "Lightly Active", value: "Light exercise/sports 1-3 days per week"},
        {label: "Moderately Active", value: "Moderate exercise/sports 3-5 days per week"},
        {label: "Very Active", value: "Hard exercise/sports 6-7 days per week"},
        {label: "Extremely Active", value: "Hard daily exercise/sports and physical job"},
      ],
    };
  },
  computed: {
    isFormValid() {
      const {name, lastName, email, biologicalStatus, activityStatus, weight, height, dateOfBirth} = this.newClient;
      return name && lastName && email && biologicalStatus && activityStatus && weight && height && dateOfBirth;
    },
  },
  methods: {
    showDialog() {
      this.visible = true;
    },
    resetForm() {
      this.newClient = {
        name: "",
        lastName: "",
        email: "",
        biologicalStatus: null,
        activityStatus: null,
        weight: null,
        height: null,
        dateOfBirth: null,
      };
    },
    addClient() {
      if (!this.isFormValid) {
        return;
      }
      ClientsService.addClient(this.newClient).catch((() => {
        this.visible = false;
        this.resetForm();
      }))
    },
  },
};
</script>

<style>
.p-field {
  margin-bottom: 10px;
  width: 100%;
}
</style>
