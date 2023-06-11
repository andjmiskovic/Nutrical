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
            <Button
                class="add-button p-button-text"
                label="ADD FOOD"
                @click="addFood(tag.data.id)"
            />
          </div>
        </template>

      </DataTable>
    </template>
  </Card>
  <AddFood ref="addFoodDialog" :plan="plan" :day="day"></AddFood>
</template>

<script>
import AddFood from "../components/AddFood.vue";

export default {
  name: "DailyFood",
  components: {
    AddFood,
  },
  data() {
    return {
      plan: {
        tags: []
      },
      day: 1,
      data: []
      // data: [
      //     {"food": "Egg", "quantity": 1, "calories": 80, "tag": "Meal 1"},
      //     {"food": "Egg", "quantity": 1, "calories": 80, "tag": "Meal 1"},
      //     {"food": "Egg", "quantity": 1, "calories": 80, "tag": "Meal 2"},
      //     {"food": "Egg", "quantity": 1, "calories": 80, "tag": "Meal 2"},
      //     {"food": "Egg", "quantity": 1, "calories": 80, "tag": "Meal 2"}
      // ]
    };
  },
  methods: {
    addFood(tagId) {
      this.$refs.addFoodDialog.tagId = tagId;
      this.$refs.addFoodDialog.visible = true;
    },
    updateData() {
      console.log("UPDATE DATA")
      console.log(this.plan.tags)
      for (let tag of this.plan.tags) {
        console.log(tag.eatenFood.length)
        if(tag.eatenFood.length === 0) {
          this.data.push({
            "food": "",
            "quantity": "",
            "calories": "",
            "tag": tag.tag,
            "id": tag.id
          })
        }
        for (let foodItem of tag["eatenFood"]) {
          this.data.push({
            "food": foodItem.name,
            "quantity": foodItem + "g",
            "calories": foodItem.calories + "kcal",
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

.tag {
  color: var(--blue);
  font-weight: 600;
  width: 100%;
}
</style>