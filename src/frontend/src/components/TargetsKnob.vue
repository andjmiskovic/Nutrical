<template>
  <Card class="border">
    <template #title>Daily Targets</template>
    <template #content>
      <div class="grid">
        <div class="col"><MyDonutChart targetsPercent='70' measurement="%" text="of Daily Targets" style="margin-top: -10px"></MyDonutChart></div>
        <div class="col">
          <div class="progress-element">
            <p class="progress-label">Protein</p>
            <p class="progress-procent">{{protein}}g / {{goalProtein}}g ({{ proteinPercent }}%)</p>
            <div id="protein-pb" class="progress-container">
              <progress
                id="protein-pb"
                max="100"
                :value="proteinPercent"
              ></progress>
            </div>
          </div>
          <div class="progress-element">
            <p class="progress-label">Fat</p>
            <p class="progress-procent">{{fat}}g / {{goalFat}}g ({{ fatPercent }}%)</p>
            <div id="carbs-pb" class="progress-container">
              <progress id="fat-pb" max="100" :value="fatPercent"></progress>
            </div>
          </div>
          <div class="progress-element">
            <div style="display: inline">
              <p class="progress-label">Carbs</p>
            <p class="progress-procent">{{carbs}}g / {{goalCarbs}}g ({{ carbsPercent }}%)</p>
            </div>
            <div id="fat-pb" class="progress-container">
              <progress
                id="carbs-pb"
                max="100"
                :value="carbsPercent"
              ></progress>
            </div>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>

<script>
import MyDonutChart from "./MyDonutChart.vue";
export default {
  mounted() {
    this.proteinPercent = Math.round((this.protein / this.goalProtein) * 100);
    this.carbsPercent = Math.round((this.carbs / this.goalCarbs) * 100);
    this.fatPercent = Math.round((this.fat / this.goalFat) * 100);
  },
  data() {
    return {
      protein: 20,
      carbs: 70,
      fat: 30,
      goalProtein: 100,
      goalCarbs: 500,
      goalFat: 50,
      proteinPercent: 0,
      carbsPercent: 0,
      fatPercent: 0,
      value: 70,
    };
  },
  components: { MyDonutChart },
};
</script>

<style scoped>
.border {
  /* border-top: 8px solid var(--pink); */
  height: 250px;
  margin: 10px;
}

.knob svg {
  margin: auto;
  display: block;
}

.knob {
  text-align: center;
}

.paragraph {
  font-size: 12px;
  margin-top: -15px;
  margin-bottom: 5px;
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

#protein-pb::-webkit-progress-value {
  background-color: var(--pink);
}

#fat-pb::-webkit-progress-value {
  background-color: var(--gray);
}

#carbs-pb::-webkit-progress-value {
  background-color: var(--green);
}
</style>