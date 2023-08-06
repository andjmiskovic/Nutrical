<template>
  <Card class="border energy-ratio">
    <template #title><i class='bx bxs-hot'></i> Macronutrient Targets</template>
    <template #content>
      <div class="progress-element">
        <p>
          <span class="progress-label">Energy</span>
          <span class="progress-procent">
            {{ calories.toFixed(0) }}/{{ goalCalories.toFixed(0) }}kcal
          </span>
        </p>
        <br>
        <p id="calories-pb" class="progress-container">
          <ProgressBar :value="calories / goalCalories * 100" :showValue="false"/>
        </p>
      </div>

      <div class="progress-element">
        <p>
          <span class="progress-label">Protein</span>
          <span class="progress-procent">
            {{ protein.value }}/{{ protein.goal }}g
          </span>
        </p>
        <br>
        <p class="progress-container">
          <ProgressBar :value="protein.value / protein.goal * 100" :showValue="false"/>
        </p>
      </div>

      <div class="progress-element">
        <p>
          <span class="progress-label">Carbs</span>
          <span class="progress-procent">
            {{ carbs.value }}/{{ carbs.goal }}g
          </span>
        </p>
        <br>
        <p class="progress-container">
          <ProgressBar :value="carbs.value / carbs.goal * 100" :showValue="false"/>
        </p>
      </div>

      <div class="progress-element">
        <p>
          <span class="progress-label">Fat</span>
          <span class="progress-procent">
            {{ fat.value }}/{{ fat.goal }}g
          </span>
        </p>
        <br>
        <p class="progress-container">
          <ProgressBar :value="fat.value / fat.goal * 100" :showValue="false"/>
        </p>
      </div>
    </template>
  </Card>
</template>

<script>
export default {
  methods: {
    updateValues(nutrients, calories, goal) {
      this.protein = nutrients.protein;
      this.carbs = nutrients.carbs;
      this.fat = nutrients.fat;

      this.calories = calories;
      this.goalCalories = goal;
    }
  },
  data() {
    return {
      calories: 0,
      goalCalories: 0,
      protein: {},
      fat: {},
      carbs: {}
    };
  }
};
</script>

<style scoped>
.border {
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