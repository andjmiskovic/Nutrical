<template>
  <Card class="border energy-ratio">
    <template #title><i class='bx bxs-hot'></i>Macronutrient Ratio</template>
    <template #content>
      <Chart type="doughnut" :data="chartData" :options="chartOptions"/>
    </template>
  </Card>
</template>

<script>
export default {
  methods: {
    updateValues(macronutrients) {
      console.log(macronutrients)
      this.protein = macronutrients.protein.percent;
      this.carbs = macronutrients.carbs.percent;
      this.fat = macronutrients.fat.percent;
    }
  },
  data() {
    return {
      protein: 20,
      carbs: 60,
      fat: 20,
      chartData: {
        labels: ['Protein', 'Carbs', 'Fat'],
        datasets: [
          {
            data: [this.protein, this.carbs, this.fat],
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
  }
};
</script>

<style>
.p-chart {
  height: 250px;
  width: 250px;
}
</style>