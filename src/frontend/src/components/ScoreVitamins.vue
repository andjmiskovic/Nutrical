<template>
  <Card class="border">
    <template #title> {{ kind }} </template>
    <template #content>
      <div>
        <DataTable :value="data" class="hide-header">
          <Column field="nutrient">
            <template #body="{ data }">
              <p
                style="cursor: pointer"
                v-tooltip.top="'About nutrient'"
                @click="showDetails(data)"
              >
                {{ data.nutrient }}
              </p>
            </template>
          </Column>
          <Column field="quantity">
            <template #body="{ data }">
              {{ data.quantity }} {{ data.unit }}
            </template>
          </Column>
          <Column field="progress" style="min-width: 5rem">
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
  props: ["kind"],
  data() {
    return {
      data: [],
    };
  },
  mounted() {
    fetch("/api/calculator/getNutrients")
      .then((response) => response.text())
      .then((data) => {
        var n = JSON.parse(data).filter((obj) => {
          return obj.kind === this.kind;
        });
        var nutrientData = [];
        for (var i = 0; i < n.length; i++) {
          nutrientData.push({
            nutrient: n[i].name,
            function: n[i].function,
            sources: n[i].sources,
            quantity: 0,
            unit: n[i].unit,
            progress: 0,
          });
        }
        this.data = nutrientData;
      });
  },
  methods: {
    showDetails(data) {
      this.$parent.openDialog(data);
    },
  },
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
  padding: 5px !important;
  font-size: 12px !important;
}
</style>