<template>
  <Card class="border">
    <template #content>
      <Calendar v-model="value" :inline="true" class="calendar" />
    </template>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      value: new Date(),
    };
  },
};
</script>

<style>
.border {
  /* border-top: 8px solid var(--green); */
  height: 250px;
  margin: 10px;
  height: min-content;
}

.p-card .p-card-content {
  padding: 0px !important;
}

.p-datepicker {
  font-size: 6px !important;
  border: none !important;
  padding: 0 !important;
}

.p-datepicker table td {
  padding: 0px !important;
  font-size: 12px !important;
}

.p-datepicker table td > span {
  width: 1.5rem !important;
  height: 1.5rem !important;
}

.p-datepicker table th {
  padding: 0px !important;
  font-size: 12px !important;
}
</style>