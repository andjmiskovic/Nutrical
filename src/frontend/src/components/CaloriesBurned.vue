<template>
  <Card class="border energy-ratio">
    <template #title><i class='bx bxs-hot'></i> Calories</template>
    <template #content>
      <div class="progress-element">
        <p>
          <span class="progress-label">Calories consumed</span>
          <span class="progress-procent">
            {{ calories.toFixed(0) }}/{{ goalCalories.toFixed(0) }}kcal
          </span>
        </p>
        <br>
        <p id="calories-pb" class="progress-container">
          <ProgressBar :value="caloriesPercent" :showValue="false"/>
        </p>
      </div>
      <Divider/>
      <p>Remaining calories: {{ (goalCalories - calories).toFixed(0) }}kcal</p>
      <!--      <p>Calories burned throw exercise: {{ exercise }}kcal</p>-->
    </template>
  </Card>
</template>

<script>
export default {
  mounted() {
    this.caloriesPercent = Math.round((this.calories / this.goalCalories) * 100);
  },
  methods: {
    updateCalories(calories, goal) {
      this.calories = calories;
      this.goalCalories = goal;
      this.caloriesPercent = Math.round((this.calories / this.goalCalories) * 100);
    }
  },
  data() {
    return {
      calories: 0,
      goalCalories: 0,
      caloriesPercent: 0,
      // exercise: 250,
    };
  }
};
</script>
<style scoped>
.border {
  height: 250px;
  margin: 10px;
}

.progress-procent {
  float: right;
}

.progress-label {
  float: left;
}

::-webkit-progress-bar {
  background-color: aliceblue;
}

.progress-container {
  width: 100%;
}

#calories-pb::-webkit-progress-value {
  background-color: var(--green);
}
</style>