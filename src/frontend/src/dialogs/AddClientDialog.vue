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
          <label for="healthStatus">Health Status</label>
          <Dropdown id="healthStatus" v-model="newClient.healthStatus" :options="healthStatusOptions" required/>
        </div>
        <div class="p-field">
          <label for="activityStatus">Activity Status</label>
          <Dropdown id="activityStatus" v-model="newClient.activityStatus" :options="activityStatusOptions" required>
            <template #option="{ option }">
              {{ option.label }} ({{ option.value }})
            </template>
          </Dropdown>
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

      <div class="p-dialog-footer">
        <Button label="Cancel" class="p-button-text" @click="cancelAddClient"/>
        <Button type="submit" label="Add" :disabled="!isFormValid"/>
      </div>
    </form>
  </Dialog>
</template>

<script>

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
        healthStatus: null,
        activityStatus: null,
        weight: null,
        height: null,
        dateOfBirth: null,
      },
      healthStatusOptions: ["MAN", "WOMAN", "PREGNANT", "BREASTFEEDING"],
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
      const {name, lastName, email, healthStatus, activityStatus, weight, height, dateOfBirth} = this.newClient;
      return name && lastName && email && healthStatus && activityStatus && weight && height && dateOfBirth;
    },
  },
  methods: {
    showDialog() {
      this.dialogVisible = true;
    },
    cancelAddClient() {
      this.dialogVisible = false;
      this.resetForm();
    },
    resetForm() {
      this.newClient = {
        name: "",
        lastName: "",
        email: "",
        healthStatus: null,
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

      // Perform the API call to add the new client using this.newClient data
      // Replace the following console.log with your actual API call
      console.log("Add new client:", this.newClient);

      this.dialogVisible = false;
      this.resetForm();
    },
  },
};
</script>

<style>
.p-field {
  margin-bottom: 10px;
}
</style>
