<template>
  <Dialog
    header="Add to Daily Diary"
    v-model:visible="visible"
    style="width: 70%"
    :maximizable="true"
    :contentStyle="{ height: '300px' }"
    :reject="closeDialog"
    :closeOnEscape="true"
  >
    <DataTable :value="customers1" :scrollable="true" scrollHeight="flex">
      <Column field="name" header="Name" style="min-width: 200px"></Column>
      <Column
        field="country.name"
        header="Country"
        style="min-width: 200px"
      ></Column>
      <Column
        field="representative.name"
        header="Representative"
        style="min-width: 200px"
      ></Column>
      <Column field="status" header="Status" style="min-width: 200px"></Column>
    </DataTable>

    <template #footer>
      <div class="grid">
        <div class="col"></div>
        <div class="col-fixed" style="width: 350px">
        <div style="display: flex; float: right">
            <div class="p-inputgroup" style="max-width: 200px; margin-right: 5px;">
              <span class="p-inputgroup-addon">Serving</span>
              <InputNumber
                mode="decimal"
                suffix="g"
                :min="0"
                :max="99999"
                v-model="serving"
              />
            </div>
            <Button label="Add" icon="pi pi-check" @click="closeDialog" />
        </div>
        </div>
      </div>
    </template>
  </Dialog>
</template>

<script>
export default {
  name: "AddFood",
  props: [],
  data() {
    return {
      customers1: [],
      visible: false,
      serving: 100,
    };
  },
  methods: {
    closeDialog() {
      this.visible = false;
    },
  },
};
</script>

<style>
</style>