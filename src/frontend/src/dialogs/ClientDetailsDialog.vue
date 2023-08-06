<template>
  <Dialog v-model:visible="visible" :modal="true" :style="{ width: '500px' }" :header="dialogHeader">
    <form @submit.prevent="editClient">
      <div class="p-fluid">
        <div class="p-field">
          <label for="email">Email</label>
          <InputText id="email" v-model="client.clientDetails.email" required disabled="true"/>
        </div>
        <div class="p-field">
          <label for="name">Name</label>
          <InputText id="name" v-model="client.clientDetails.name" required/>
        </div>
        <div class="p-field">
          <label for="lastName">Last Name</label>
          <InputText id="lastName" v-model="client.clientDetails.surname" required/>
        </div>
        <div class="p-field">
          <label for="healthStatus">Health Status</label>
          <Dropdown id="healthStatus" v-model="client.clientDetails.clientData.healthStatus"
                    :options="healthStatusOptions" required/>
        </div>
        <div class="p-field">
          <label for="activityStatus">Activity Status</label>
          <Dropdown id="activityStatus" v-model="client.clientDetails.clientData.activityStatus"
                    :options="activityStatusOptions"
                    optionLabel="value" class="p-field"/>
        </div>
        <div class="p-field">
          <label for="weight">Weight (kg)</label>
          <InputNumber id="weight" v-model="client.clientDetails.clientData.weight" required :mode="'decimal'" :min="0"
                       :max="999"/>
        </div>
        <div class="p-field">
          <label for="height">Height (cm)</label>
          <InputNumber id="height" v-model="client.clientDetails.clientData.height" required :mode="'decimal'" :min="0"
                       :max="999"/>
        </div>
        <div class="p-field">
          <label for="dateOfBirth">Date of Birth</label>
          <Calendar id="dateOfBirth" v-model="client.clientDetails.clientData.dateOfBirth" required/>
        </div>
        <div class="p-field">
          <label for="bmi" v-tooltip="'About BMI'" style="cursor: pointer"
                 @click="this.$refs.bmi.visibility = true">BMI</label>
          <InputText id="bmi" v-model="client.bmi" disabled="true"/>
        </div>
        <div class="p-field">
          <label for="calories" v-tooltip="'Calculation'" style="cursor: pointer"
                 @click="this.$refs.calories.visibility = true">Maintenance calories</label>
          <InputText id="calories" v-model="client.calories" disabled="true"/>
        </div>
        <div class="p-field">
          <label for="goal">Daily calorie goal</label>
          <InputText id="goal" v-model="client.clientDetails.clientData.calorieGoal"/>
        </div>
        <div class="p-field">
          <label>Macronutrient split</label>
          <div class="grid p-fluid">
            <div class="field col-4" style="padding: 10px">
              <InputNumber v-model="client.clientDetails.clientData.proteinPercent" prefix="P: " suffix="%" :min="1" :max="99"/>
            </div>
            <div class="field col-4" style="padding: 10px">
              <InputNumber v-model="client.clientDetails.clientData.carbsPercent" prefix="C: " suffix="%" :min="1" :max="99"/>
            </div>
            <div class="field col-4" style="padding: 10px">
              <InputNumber v-model="client.clientDetails.clientData.fatPercent" prefix="F: " suffix="%" :min="1" :max="99"/>
            </div>
          </div>
        </div>
      </div>

      <Button type="submit" style="width: 100%" label="Save changes" :disabled="!isFormValid"/>
    </form>
  </Dialog>
  <BMIDialog ref="bmi"></BMIDialog>
  <CaloriesDetails ref="calories"></CaloriesDetails>
</template>

<script>

import ClientsService from "@/services/ClientsService";
import BMIDialog from "@/dialogs/BMI";
import CaloriesDetails from "@/dialogs/CaloriesDetails";

export default {
  name: "ClientDetailsDialog",
  components: {BMIDialog, CaloriesDetails},
  data() {
    return {
      visible: false,
      dialogHeader: "Client Details",
      client: {
        clientDetails: {
          name: "",
          lastName: "",
          email: "",
          clientData: {
            healthStatus: null,
            activityStatus: null,
            weight: null,
            height: null,
            dateOfBirth: null,
            calorieGoal: 0,
            proteinPercent: 0,
            carbsPercent: 0,
            fatPercent: 0,
            additionalInformation: "",
          }
        },
        bmi: 0,
        calories: 0
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
      const {name, lastName, email, healthStatus, activityStatus, weight, height, dateOfBirth} = this.client;
      return name && lastName && email && healthStatus && activityStatus && weight && height && dateOfBirth;
    },
  },
  methods: {
    showDialog() {
      this.visible = true;
    },
    editClient() {
      if (!this.isFormValid) {
        return;
      }
      ClientsService.editClient(this.client).catch((() => {
        this.$toast.add({severity:'success', summary: 'Success', detail:'Client data updated.', life: 3000});
      }))
    },
  },
};
</script>

<style>
.p-field {
  margin-bottom: 10px;
}
</style>
