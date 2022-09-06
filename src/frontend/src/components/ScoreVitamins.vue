<template>
  <Card class="border">
    <template #title> Vitamins </template>
    <template #content>
      <div>
        <DataTable :value="data" class="hide-header">
          <Column field="name" style="min-width: 15rem">
            <template #body="{ data }">
              {{ data.name }}
            </template>
          </Column>
          <Column field="quantity" style="min-width: 5rem">
            <template #body="{ data }">
              {{ data.quantity }}
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
      data: [
        {'name': 'Vitamin A', 'quantity': '5mg', 'progress': 70 },
        {'name': 'Vitamin B', 'quantity': '5mg', 'progress': 70 },
        {'name': 'Vitamin C', 'quantity': '5mg', 'progress': 70 },
      ],
    };
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
    padding: 8px !important; 
    font-size: 15px !important;
}

</style>