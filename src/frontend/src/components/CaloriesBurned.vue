<template>
  <Card class="border energy-ratio">
    <template #title><i class='bx bxs-hot'></i> Calories</template>
    <template #content>
      <div class="progress-element">
        <p class="progress-label">Calories consumed</p>
        <p class="progress-procent">
          {{ calories }}/{{ goalCalories }}kcal
        </p>
        <div id="calories-pb" class="progress-container">
          <progress
              max="100"
              :value="caloriesPercent"
          ></progress>
        </div>
      </div>
      <Divider/>
      <p>Remaining calories: {{ goalCalories - calories }}kcal</p>
<!--      <p>Calories burned throw exercise: {{ exercise }}kcal</p>-->
    </template>
  </Card>
</template>

<script>
export default {
  mounted() {
    this.caloriesPercent = Math.round((this.calories / this.goalCalories) * 100);
  },
  data() {
    return {
      calories: 2000,
      goalCalories: 2300,
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
  margin-top: -20px;
  top: 0;
  right: 0;
}

::-webkit-progress-bar {
  background-color: aliceblue;
}

progress {
  -webkit-appearance: none;
  width: 100%;
}

#calories-pb::-webkit-progress-value {
  background-color: var(--green);
}
</style>