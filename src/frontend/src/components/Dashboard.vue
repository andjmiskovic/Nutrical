<template>
  <NavBar></NavBar>
  <img
    class="dashboard-background"
    src="../assets/images/dashboard-background.jpg"
    alt=""
  />
  <h2 class="welcome">Welcome back, Andjela</h2>
  <div style="margin-left: 100px; margin-top: 10px">
    <div class="grid" style="width: 100%">
      <div class="col-3">
        <MyCalendar></MyCalendar>
        <EnergyRatio></EnergyRatio>
        <!-- <WeeklyCalories></WeeklyCalories> -->
      </div>
      <div class="col-9">
        <div class="grid">
          <div class="col-fixed" style="width: 100px">
            <Button
              style="float: left; margin-left: 20px;"
              v-tooltip.top="'Day before'"
              icon="pi pi-angle-left"
              class="p-button-rounded p-button-primary"
            />
          </div>
          <div class="col">
          <h3 style="text-align: center">9th of September, 2022</h3>
          </div>
          <div class="col-fixed" style="width: 100px">
            <Button
              style="float: right; margin-right: 20px;"
              v-tooltip.top="'Day after'"
              icon="pi pi-angle-right"
              class="p-button-rounded p-button-primary"
            />
          </div>
        </div>
        <div class="grid" style="margin: 10px">
          <div class="col">
            <Button class="add-button p-button-text"
              label="ADD FOOD"/>
          </div>
          <div class="col">
            <Button class="add-button p-button-text"
              label="ADD EXERCISE"/>
          </div>
          <div class="col">
            <Button class="add-button p-button-text"
              label="ADD NOTES"/>
          </div>
        </div>
        <div class="grid">
          <div class="col-6"><TargetsKnob></TargetsKnob></div>
          <div class="col-6"><CaloriesBurned></CaloriesBurned></div>
        </div>
        <ScoreVitamins></ScoreVitamins>
      </div>
    </div>
  </div>
  <AddFood ref="addFoodDialog"></AddFood>
  <Dialog v-model:visible="nutrientsDialogVisible" style="width: 60%">
    <template #header>
      <h3><i class="bx bxs-info-circle"></i> {{ nutrient.nutrient }}</h3>
    </template>
    <h4>Function</h4>
    <p>{{ nutrient.function }}</p>
    <h4>Sources</h4>
    <p>{{ nutrient.sources }}</p>
  </Dialog>
</template>

<script>
import NavBar from "./NavBar.vue";
import EnergyRatio from "./EnergyRatio.vue";
import TargetsKnob from "./TargetsKnob.vue";
import CaloriesBurned from "./CaloriesBurned.vue";
import MyCalendar from "./MyCalendar.vue";
import ScoreVitamins from "./ScoreVitamins.vue";
import AddFood from "./AddFood.vue";
export default {
  name: "Dashboard",
  components: {
    NavBar,
    EnergyRatio,
    TargetsKnob,
    CaloriesBurned,
    MyCalendar,
    ScoreVitamins,
    AddFood,
  },
  data() {
    return {
      nutrient: {
        function: "",
        source: "",
      },
      nutrientsDialogVisible: false,
    };
  },
  methods: {
    openDialog(data) {
      this.nutrient = data;
      this.nutrientsDialogVisible = true;
    },
    addFood() {
      this.$refs.addFoodDialog.visible = true;
    },
  },
};
</script>

<style scoped>
.dashboard-background {
  height: 200px;
  width: 100%;
  margin-top: -10px;
  object-fit: cover;
}

.welcome {
  margin-top: -65px;
  margin-left: 90px;
  color: white;
  text-shadow: 2px 2px 10px black;
  font-size: 35px;
}

.energy-ration {
  display: flex;
}

.add-button {
  width: 100%;
  font-weight: 600;
  text-align: center;
  color: var(--gray) !important;
}
</style>