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
          <div class="tag" >
            <span>{{ tag.data.tag }}</span>
            <div style="float: right">
              <Button icon="pi pi-plus" @click="addFood(tag.data.id)"
                      class="p-button-sm blue p-button-rounded p-button-text"/>
              <Button icon="pi pi-ellipsis-v" class="p-button-sm p-button-text blue p-button-rounded" @click="toggle"
                      aria-haspopup="true" aria-controls="overlay_menu"/>
              <Menu id="overlay_menu" ref="menu" :model="items" :popup="true"/>
            </div>
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
      data: [],
      menuVisible: false,
      items: [
        {
          label: 'Tag options',
          items: [
            {
              label: 'Rename',
              icon: 'pi pi-pencil',

            },
            {
              label: 'Delete tag',
              icon: 'pi pi-trash',
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
    toggle(event) {
      this.$refs.menu.toggle(event);
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