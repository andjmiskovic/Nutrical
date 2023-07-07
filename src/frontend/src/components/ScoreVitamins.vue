<template>
  <Card class="border">
    <template #title> {{ kind }}</template>
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
              {{ data.quantity.toFixed(1) }} {{ data.unit }}
            </template>
          </Column>
          <Column field="progress" style="width: 6rem">
            <template #body="{ data }">
              <ProgressBar :value="data.progress" :showValue="false"/>
            </template>
          </Column>
          <Column field="percent">
            <template #body="{ data }">
              {{ data.progress }}%
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
  methods: {
    showDetails(data) {
      this.$parent.openDialog(data);
    },
    updateValues(nutrients) {
      console.log(nutrients);
      const nutrientData = [];
      for (let n of nutrients) {
        if (n.nutrient.kind === this.kind) {
          nutrientData.push({
            nutrient: n.nutrient.name,
            function: n.nutrient.function,
            sources: n.nutrient.sources,
            quantity: n.amount,
            unit: n.nutrient.unit,
            progress: (n.amount / n.goal * 100).toFixed(1),
          });
        }
      }
      this.data = nutrientData;
    }
  },
};
</script>

<style scoped>
::v-deep(.p-datatable .p-datatable-thead tr th) {
  padding: 0 !important;
}

::v-deep(.p-datatable .p-datatable-tbody tr td) {
  padding: 5px !important;
  font-size: 12px !important;
}

::v-deep(.p-progressbar .p-progressbar-value) {
  background: var(--green);
}
</style>