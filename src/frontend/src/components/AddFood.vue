<template>
  <Dialog
    header="Add food to Daily Diary"
    v-model:visible="visible"
    style="width: 70%"
    :maximizable="true"
    :reject="closeDialog"
    :closeOnEscape="true"
  >
    <DataTable
      class="p-datatable-sm"
      selectionMode="single"
      v-model:selection="selectedFood"
      :value="food"
      :scrollable="true"
    >
      <Column style="min-width: 50%" field="name" header="Name"></Column>
      <Column field="calories" header="Calories"></Column>
      <Column field="protein" header="Protein"></Column>
      <Column field="carbs" header="Carbs"></Column>
      <Column field="fat" header="Fat"></Column>
    </DataTable>

    <template #footer>
      <div class="grid" style="margin-top: 10px">
        <div class="col">
          <span class="p-input-icon-left" style="float: left">
            <i class="pi pi-search" />
            <InputText v-model="search" @keyup="searchFood" placeholder="Search food..." />
          </span>
        </div>
        <div class="col">
            <div class="p-inputgroup" style="max-width: 200px; float: right; margin-right: 5px">
              <span class="p-inputgroup-addon">Serving</span>
              <InputNumber
                mode="decimal"
                suffix="g"
                :min="0"
                :max="99999"
                v-model="serving"
              />
            </div>
        </div>
        <div class="col-fixed" style="width: 100px">
          <Button label="Add" icon="pi pi-check" @click="closeDialog" />
        </div>
      </div>
    </template>
  </Dialog>
</template>

<script>
import { FilterMatchMode } from "primevue/api";

export default {
  name: "AddFood",
  props: [],
  data() {
    return {
      visible: false,
      serving: 100,
      search: "",
      food: [],
      selectedFood: null,
      filters: {
        name: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
      },
    };
  },
  mounted() {
    this.searchFood()
  },
  methods: {
    searchFood() {
      fetch("/api/calculator/getFood?search=" + this.search + "&limit=100")
      .then((response) => response.text())
      .then((data) => {
        this.food = JSON.parse(data);
        console.log(this.food);
      });
    },
    closeDialog() {
      this.visible = false;
    },
  },
};
</script>

<style>
</style>