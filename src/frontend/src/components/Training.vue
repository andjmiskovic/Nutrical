<template>
  <Card class="border" style="min-height: 150px">
    <template #header>
      <img alt="user header" src="../assets/images/gym.jpg">
      <SelectButton @click="saveTrainingPlan()" v-model="restDay" :options="options"
                    style="margin-top: -20px; margin-left: 20px"/>
    </template>
    <template #content>
      <div v-if="restDay === 'Training'">
        <div v-if="editing">
          <Textarea v-model="trainingPlan" style="width: 100%; max-height: 130px; min-height: 70px" rows="50" cols="30"/>
          <Button class="p-button-text" @click="saveTrainingPlan()" style="width: 100%" label="Save"/>
        </div>
        <div v-if="!editing">
          <p>
            <i class="pi pi-pencil" style="cursor: pointer; color: var(--pink)" @click="editing = !editing"
               v-tooltip="'Edit'"></i>
            {{ trainingPlan }}<span style="color: var(--light-gray)" v-if="trainingPlan.length === 0">Write something...</span>
          </p>
        </div>
      </div>
    </template>
  </Card>
</template>

<script>
import PlanService from "../services/PlanService";

export default {
  name: "Training",
  props: ["planId", "day"],
  methods: {
    saveTrainingPlan() {
      if (this.restDay === "Rest day") {
        this.trainingPlan = "";
      }
      let body = {
        "training": this.trainingPlan,
        "planId": this.planId,
        "day": this.day
      }
      PlanService.saveTraining(body).then(() => {
        this.editing = false;
      });
    }
  },
  data() {
    return {
      trainingPlan: '',
      editing: false,
      restDay: "Training",
      options: ["Rest day", "Training"]
    };
  }
};
</script>

<style scoped>
.border {
  margin: 15px;
}
</style>