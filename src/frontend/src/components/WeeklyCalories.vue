<template>
  <Card class="border">
    <template #title> Last Week's Calorie Intake </template>
    <template #content>
      <Chart
        type="bar"
        :data="basicData"
        style="min-width: 400px; margin-bottom: 10px;"
        :options="lightOptions"
      />
      <p>Average calorie intake: {{ average }}kcal</p>
    </template>
  </Card>
</template>

<script>
export default {
  mounted() {
    this.basicData.labels = this.Last7Days();
    this.average = this.calculateAverage(this.basicData.datasets[0].data);
  },
  methods: {
    Last7Days() {
      var result = [];
      for (var i = 7; i > 0; i--) {
        var d = new Date();
        d.setDate(d.getDate() - i);
        result.push(
          d.toLocaleDateString("en-us", { weekday: "long" }).slice(0, 3)
        );
      }
      return result;
    },
    calculateAverage(arr) {
      return Math.round(arr.reduce((a, b) => a + b, 0) / arr.length, 0);
    },
  },
  data() {
    return {
      average: 0,
      basicData: {
        datasets: [
          {
            backgroundColor: "#277BC0",
            data: [2100, 2300, 2000, 1900, 2400, 1800, 2500],
          },
        ],
      },
      lightOptions: {
        plugins: {
          legend: {
            display: false
          },
        },
      },
    };
  },
};
</script>

<style scoped>
.border {
  /* border-top: 8px solid var(--blue); */
  /*height: 250px;*/
  margin: 10px;
  height: min-content;
}
</style>