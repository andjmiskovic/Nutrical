<template>
  <Card class="border">
    <template #content>
      <DataTable :value="data" rowGroupMode="subheader" groupRowsBy="meal" class="hide-header" responsiveLayout="scroll"
                 sortMode="single" sortField="meal.data.meal" :sortOrder="1" scrollable style="min-height: 200px">
        <Column field="food" header="Food">
          <template #body="meal">{{ meal.data.food }}</template>
        </Column>
        <Column field="quantity" header="Quantity">
          <template #body="meal"> {{ meal.data.quantity }}</template>
        </Column>
        <Column field="energy" header="Energy">
          <template #body="meal"> {{ meal.data.calories }}</template>
        </Column>

        <template #groupheader="meal">
          <span style="margin-right: 30px">{{ meal.data.meal }}</span>
          <Button icon="pi pi-plus" @click="addFood(meal.data.id)" v-tooltip.top="'Add food'"
                  class="p-button-sm blue p-button-rounded p-button-text"/>
          <Button icon="pi pi-ellipsis-v" class="p-button-sm p-button-text blue p-button-rounded"
                  @click="toggle($event, meal)"
                  aria-haspopup="true" aria-controls="overlay_menu"/>
          <Menu id="overlay_menu" ref="menu" :model="items" :popup="true"/>
        </template>

      </DataTable>
    </template>
  </Card>
  <AddFood ref="addFoodDialog" :plan="plan" :day="day"></AddFood>
  <AddMeal ref="editMealDialog" :plan="plan" :day="day"></AddMeal>
</template>

<script>
import AddFood from "../dialogs/AddFood.vue";
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
        meals: []
      },
      day: 1,
      data: [],
      menuVisible: false,
      selectedTagId: 0,
      selectedTagName: '',
      items: [
        {
          label: 'Meal options',
          items: [
            {
              label: 'Rename',
              icon: 'pi pi-pencil',
              command: () => {
                this.$refs.editMealDialog.visible = true;
                this.$refs.editMealDialog.tagName = this.selectedTagName;
                this.$refs.editMealDialog.tagId = this.selectedTagId;
              }
            },
            {
              label: 'Delete meal',
              icon: 'pi pi-trash',
              command: () => {
                let body = {
                  "tagId": this.selectedTagId,
                  "planId": this.plan.id,
                  "day": this.day,
                }
                PlanService.removeTag(body).then(() => {
                  this.$parent.reloadPlan();
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
    toggle(event, meal) {
      this.$refs.menu.toggle(event);
      this.selectTag(meal);
    },
    selectTag(meal) {
      this.selectedTagId = meal.data.id;
      this.selectedTagName = meal.data.name;
    },
    updateData() {
      this.data = [];
      for (let meal of this.plan.meals) {
        if (meal.eatenFood.length === 0) {
          this.data.push({
            "food": "",
            "quantity": "",
            "calories": "",
            "meal": meal.name,
            "id": meal.id
          })
        }
        for (let food of meal["eatenFood"]) {
          this.data.push({
            "food": food["foodItem"].name,
            "quantity": food.quantity + "g",
            "calories": food["foodItem"].calories + "kcal",
            "meal": meal.name,
            "id": meal.id
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

.p-rowgroup-footer td {
  font-weight: 700;
}

:deep(.p-rowgroup-header) span {
  font-weight: 700;
}

:deep(.p-rowgroup-header) .p-row-toggler {
  vertical-align: middle;
  margin-right: .25rem;
}
</style>