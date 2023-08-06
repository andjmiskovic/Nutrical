<template>
  <Card class="border">
    <template #title> {{ kind }}</template>
    <template #content>
      <div>
        <DataTable :value="data" class="hide-header">
          <Column field="nutrient" style="width: 12rem">
            <template #body="{ data }">
              <span
                  style="cursor: pointer"
                  v-tooltip.top="'About nutrient'"
                  @click="showDetails(data)"
              >
                {{ data.nutrient }}
              </span>
            </template>
          </Column>
          <Column field="quantity" style="width: 5rem">
            <template #body="{ data }">
              {{ data.quantity.toFixed(1) }} {{ data.unit }}
            </template>
          </Column>
          <Column field="progress" style="width: 8rem">
            <template #body="{ data }">
              <ProgressBar v-if="!isNaN(data.progress)" :value="data.progress" :showValue="false"
                           :class="[{'green-progress' : data.progress >= 100}, {'red-progress' : data.alert}]"/>
            </template>
          </Column>
          <Column field="percent">
            <template #body="{ data }">
              <span v-if="!isNaN(data.progress)">{{ data.progress }}%</span>
              <span v-if="isNaN(data.progress)">No target</span>
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
    alert(nutrient) {
      if (nutrient.nutrient.recommended.max) {
        return nutrient.amount > nutrient.nutrient.recommended.max;
      }
      return false;
    },
    getProgress(nutrient) {
      if (nutrient.goal === 0)
        return NaN;
      return +(nutrient.amount / nutrient.goal * 100).toFixed(1);
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
            alert: this.alert(n),
            progress: this.getProgress(n),
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

::v-deep(.p-progressbar.green-progress .p-progressbar-value) {
  background: var(--green);
}

::v-deep(.p-progressbar.red-progress .p-progressbar-value) {
  background: var(--red);
}
</style>