<template>
  <Card class="border energy-ratio">
    <template #title><i class='bx bxs-hot'></i>Macronutrient Ratio</template>
    <template #content>
      <Chart ref="chart" type="doughnut" :data="chartData"/>
    </template>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      chartData: {
        labels: ['Protein', 'Carbs', 'Fat'],
        datasets: [
          {
            data: [20, 60, 20],
            backgroundColor: [
              "#23CE6B",
              "#277BC0",
              "#FFCE56"
            ],
            hoverBackgroundColor: [
              "#23CE6B",
              "#277BC0",
              "#FFCE56"
            ]
          }
        ]
      }
    }
  },
  methods: {
    updateValues(macronutrients) {
      this.chartData.datasets.data = [macronutrients.protein.percent, macronutrients.carbs.percent, macronutrients.fat.percent];
      console.log(this.chartData);
      this.$refs.chart.dataset = this.chartData.datasets;
      this.$refs.chart.refresh();
    }
  }
};
</script>

<style>
.p-chart {
  height: 250px;
  width: 250px;
}
</style>