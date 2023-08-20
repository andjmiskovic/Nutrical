<template>
  <Dialog v-model:visible="visible" :modal="true" :style="{ width: '500px' }" :header="dialogHeader">
    <form @submit.prevent="send">
      <div class="p-fluid">
        <div class="p-field">
          <label for="name">Email text</label>
          <InputText id="name" v-model="text"/>
        </div>
      </div>

      <Button type="submit" style="width: 100%" label="Send plan"/>
    </form>
  </Dialog>
</template>

<script>
import PlanService from "@/services/PlanService";

export default {
  name: "SendPlanDialog",
  props: ["plan"],
  data() {
    return {
      visible: false,
      dialogHeader: "Send plan via mail",
    };
  },
  methods: {
    showDialog() {
      this.visible = true;
    },
    addMeal() {
      let body = {
        "planId": this.plan.id,
        "text": this.text
      }
      PlanService.sendPlan(body).then((() => {
        this.visible = false;
        console.log("Email is sending.")
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
