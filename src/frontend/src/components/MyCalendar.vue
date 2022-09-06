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
        }
    }
}
</script>

<style>
.border {
  border-top: 8px solid var(--green);
  height: 250px;
  margin: 10px;
  height: min-content;
}

.p-datepicker {
    font-size: 6px !important;
    border: none !important;
    padding: 0 !important;
}

.p-datepicker table td {
  padding: 0px !important;
}
</style>