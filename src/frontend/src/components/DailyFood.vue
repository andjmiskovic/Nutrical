<template>
  <Card class="border">
    <template #content>
        <DataTable :value="data" class="hide-header" style="min-height: 200px">
          <Column field="name">
            <template #header>Food</template>
            <template #body="{ data }">
              {{ data.food }}
            </template>
          </Column>
          <Column field="quantity">
            <template #header>Quantity</template>
            <template #body="{ data }"> {{ data.quantity }}g </template>
          </Column>
          <Column field="energy">
            <template #header>Energy</template>
            <template #body="{ data }"> {{ data.quantity }}kcal </template>
          </Column>
        </DataTable>
    </template>
  </Card>
</template>
  
<script>
export default {
  props: ["data"],
  data() {
    return {};
  },
  mounted() {},
  methods: {},
};
</script>
  
<style scoped>
::v-deep(.p-card-body) {
    padding: 0 !important;
}
</style>