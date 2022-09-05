<template>
  <Card class="border energy-ratio">
    <template #title> Energy Ratio </template>
    <template #content>
      <div class="grid">
        <div class="col">
          <Chart
            type="doughnut"
            :data="chartData"
            width="150"
            :options="lightOptions"
          />
        </div>
        <div class="col">
          <div class="progress-element">
            <p class="progress-label" style="vertical-align: middle;">
              Fat - {{fatPercent}}%<br>
              Carbs - {{carbsPercent}}%<br>
              Protein - {{proteinPercent}}%
            </p>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>

<script>
export default {
  mounted() {
    var total = this.fat + this.carbs + this.protein;
    this.fatPercent = Math.round(this.fat / total * 100); 
    this.carbsPercent = Math.round(this.carbs / total * 100); 
    this.proteinPercent = 100 - this.fatPercent - this.carbsPercent; 
    this.chartData.datasets[0].data[0] = this.fatPercent;
    this.chartData.datasets[0].data[1] = this.carbsPercent;
    this.chartData.datasets[0].data[2] = this.proteinPercent;
  },
  data() {
    return {
      fat: 10,
      carbs: 100,
      protein: 20,
      fatPercent: 0,
      carbsPercent: 0,
      proteinPercent: 0,
      chartData: {
        // labels: ['Fats', 'Carbs', 'Protein'],
        datasets: [
          {
            data: [33, 34, 33],
            backgroundColor: ["#50514F", "#23CE6B", "#A846A0"],
          },
        ],
      },
      lightOptions: {
        plugins: {
          legend: {
            labels: {
              color: "#50514F",
            },
          },
        },
      },
    };
  },
};
</script>

<style scoped>
.border {
  border-top: 8px solid var(--gray);
  height: 250px;
}

.energy-ratio {
  max-width: 300px;
}
</style>