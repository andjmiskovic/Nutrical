<template>
  <Dialog v-model:visible="visible" :modal="true" :style="{ width: '500px' }" :header="!tagId ? 'Add meal' : 'Rename meal'">
    <form @submit.prevent="addMeal">
      <div class="p-fluid">
        <div class="p-field">
          <label for="name">Meal name</label>
          <InputText id="name" v-model="newTagName" required/>
        </div>
      </div>

      <Button v-if="!tagId" type="submit" style="width: 100%" label="Create" :disabled="!isFormValid"/>
      <Button v-if="tagId" type="submit" style="width: 100%" label="Rename" :disabled="!isFormValid"/>
    </form>
  </Dialog>
</template>

<script>
import PlanService from "@/services/PlanService";

export default {
  name: "AddMeal",
  props: ["plan", "day"],
  data() {
    return {
      visible: false,
      dialogHeader: "Add Meal",
      newTagName: "",
      tagId: "",
      tagName: ""
    };
  },
  computed: {
    isFormValid() {
      return this.newTagName !== "";
    },
  },
  mounted() {
    this.newTagName = this.tagName;
    if (this.tagId) {
      this.dialogHeader = "Rename meal";
    }
  },
  methods: {
    showDialog() {
      this.visible = true;
    },
    addMeal() {
      if (!this.isFormValid) {
        return;
      }
      let body = {
        "tagName": this.newTagName,
        "tagId": this.tagId,
        "planId": this.plan.id,
        "day": this.day
      }
      if (!this.tagId) {
        PlanService.addTag(body).then((() => {
          this.visible = false;
          this.$parent.$parent.reloadPlan();
        }))
      } else {
        PlanService.renameTag(body).then((() => {
          this.visible = false;
          this.$parent.$parent.reloadPlan();
        }))
      }
    },
  },
};
</script>

<style>
.p-field {
  margin-bottom: 10px;
}
</style>
