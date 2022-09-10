<template>
  <Card class="border">
    <template #title> Vitamins </template>
    <template #content>
      <div>
        <DataTable :value="data" class="hide-header">
          <Column field="nutrient" style="min-width: 15rem">
            <template #body="{ data }">
              <p style="cursor: pointer" v-tooltip.top="'About nutrient'" @click="showDetails(data)">{{ data.nutrient }}</p>
            </template>
          </Column>
          <Column field="quantity" style="min-width: 5rem">
            <template #body="{ data }">
              {{ data.quantity }} {{ data.unit }}
            </template>
          </Column>
          <Column field="progress" style="min-width: 10rem">
            <template #body="{ data }">
              <ProgressBar :value="data.progress" />
            </template>
          </Column>
        </DataTable>
      </div>
    </template>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      nutrients: [],
      data: [],
    };
  },
  mounted() {
    fetch("/api/calculator/getNutrients")
      .then((response) => response.text())
      .then((data) => {
        this.nutrients = JSON.parse(data);
        for (var i = 0; i < this.nutrients.length; i++) {
          this.data.push({
            nutrient: this.nutrients[i].name,
            function: this.nutrients[i].function,
            sources: this.nutrients[i].sources,
            quantity: 0,
            unit: this.nutrients[i].unit,
            progress: 0,
          });
        }
      });
  },
  methods: {
    showDetails(data) {
        this.$parent.openDialog(data);
    }
  }
};
</script>

<style scoped>
::v-deep(.p-progressbar) .p-progressbar-value {
  background-color: var(--green);
  font-size: 12px;
}

::v-deep(.p-datatable .p-datatable-thead tr th) {
  padding: 0 !important;
}

::v-deep(.p-datatable .p-datatable-tbody tr td) {
  padding: 8px !important;
  font-size: 15px !important;
}
</style>