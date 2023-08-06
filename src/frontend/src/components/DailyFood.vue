<template>
  <Card class="border">
    <template #content>
      <DataTable :value="data" rowGroupMode="subheader" groupRowsBy="tag" class="hide-header"
                 style="min-height: 200px">
        <Column field="food" header="Food">
          <template #body="tag">{{ tag.data.food }}</template>
        </Column>
        <Column field="quantity" header="Quantity">
          <template #body="tag"> {{ tag.data.quantity }}</template>
        </Column>
        <Column field="energy" header="Energy">
          <template #body="tag"> {{ tag.data.calories }}</template>
        </Column>

        <template #groupheader="tag">
          <div class="tag">
            <span>{{ tag.data.tag }}</span>
            <div style="float: right">
              <Button icon="pi pi-plus" @click="addFood(tag.data.id)" v-tooltip.top="'Add food'"
                      class="p-button-sm blue p-button-rounded p-button-text"/>
              <Button icon="pi pi-ellipsis-v" class="p-button-sm p-button-text blue p-button-rounded"
                      @click="toggle($event, tag)"
                      aria-haspopup="true" aria-controls="overlay_menu"/>
              <Menu id="overlay_menu" ref="menu" :model="items" :popup="true"/>
            </div>
          </div>
        </template>

      </DataTable>
    </template>
  </Card>
  <AddFood ref="addFoodDialog" :plan="plan" :day="day"></AddFood>
  <AddMeal ref="editMealDialog" :plan="plan" :day="day" :tag-name="selectedTagName" :tag-id="selectedTagId"></AddMeal>
</template>

<script>
import AddFood from "../components/AddFood.vue";
import AddMeal from "../dialogs/AddMeal.vue";
import PlanService from "@/services/PlanService";

export default {
  name: "DailyFood",
  components: {
    AddFood,
    AddMeal,
  },
  data() {
    return {
      plan: {
        tags: []
      },
      day: 1,
      data: [],
      dailyPlanId: 0,
      menuVisible: false,
      selectedTagId: 0,
      selectedTagName: '',
      items: [
        {
          label: 'Tag options',
          items: [
            {
              label: 'Rename',
              icon: 'pi pi-pencil',
              command: () => {
                console.log("RENAME" + this.selectedTagId)
              }
            },
            {
              label: 'Delete tag',
              icon: 'pi pi-trash',
              command: () => {
                let body = {
                  "tagId": this.selectedTagId,
                  "planId": this.plan.id,
                  "day": this.day,
                }
                PlanService.removeTag(body).then(() => {
                  this.$parent.$parent.reloadPlan();
                })
              }
            }
          ]
        }]
    };
  },
  methods: {
    addFood(tagId) {
      this.$refs.addFoodDialog.tagId = tagId;
      this.$refs.addFoodDialog.visible = true;
    },
    toggle(event, tag) {
      this.$refs.menu.toggle(event);
      this.selectTag(tag);
    },
    selectTag(tag) {
      this.selectedTagId = tag.data.id;
      this.selectedTagName = tag.data.tag;
    },
    updateData() {
      this.data = [];
      for (let tag of this.plan.tags) {
        if (tag.eatenFood.length === 0) {
          this.data.push({
            "food": "",
            "quantity": "",
            "calories": "",
            "tag": tag.tag,
            "id": tag.id
          })
        }
        for (let food of tag["eatenFood"]) {
          this.data.push({
            "food": food["foodItem"].name,
            "quantity": food.quantity + "g",
            "calories": food["foodItem"].calories + "kcal",
            "tag": tag.tag,
            "id": tag.id
          })
        }
      }
    }
  }
};
</script>

<style scoped>
::v-deep(.p-card-body) {
  padding: 0 !important;
}

.blue {
  color: var(--blue);
}

.tag {
  color: var(--blue);
  font-weight: 600;
  width: 100%;
}
</style>