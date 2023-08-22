<template>
  <Card class="border" style="min-height: 180px">
    <template #title>
      Notes
    </template>
    <template #content>
      <div v-if="editing">
          <Textarea v-model="notes"
                    style="width: 100%; max-height: 130px; min-height: 70px" rows="50" cols="30"/>
        <Button class="p-button-text" @click="saveNotes()" style="width: 100%" label="Save"/>
      </div>
      <div v-if="!editing">
        <p>
          <i class="pi pi-pencil" style="cursor: pointer; color: var(--pink)" @click="editing = !editing"
             v-tooltip="'Edit'"></i>
          {{ notes }}<span style="color: var(--light-gray)" v-if="notes.length === 0">Write something...</span>
        </p>
      </div>
    </template>
  </Card>
</template>

<script>
import PlanService from "../services/PlanService";

export default {
  name: "Notes",
  props: ["planId", "day"],
  methods: {
    saveNotes() {
      let body = {
        "training": this.notes,
        "planId": this.planId,
        "day": this.day
      }
      PlanService.saveNotes(body).then(() => {
        this.editing = false;
      });
    }
  },
  data() {
    return {
      notes: '',
      editing: false,
    };
  }
};
</script>

<style scoped>
.border {
  margin: 15px;
}
</style>