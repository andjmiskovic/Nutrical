<template>
  <NavBar active-page="dashboard"></NavBar>
  <img
      class="dashboard-background"
      src="../assets/images/dashboard-background.jpg"
      alt=""
  />
  <h2 class="welcome">Plan for {{plan.userEmail}}</h2>
  <div style="margin: 100px; margin-top: 30px">
    <div class="grid" style="width: 90%; margin: auto">
      <div class="col-9">
        <div class="grid" style="margin-top: 20px">
          <div class="col-fixed" style="width: 100px">
            <Button
                v-if="day > 1"
                @click="previousDay"
                style="float: left; margin-left: 20px"
                v-tooltip.top="'Day before'"
                icon="pi pi-angle-left"
                class="p-button-rounded p-button-primary"
            />
          </div>
          <div class="col">
            <h3 style="text-align: center">Day #{{ day }}</h3>
          </div>
          <div class="col-fixed" style="width: 100px">
            <Button
                @click="nextDay"
                style="float: right; margin-right: 20px"
                v-tooltip.top="'Day after'"
                icon="pi pi-angle-right"
                class="p-button-rounded p-button-primary"
            />
          </div>
        </div>
        <div class="grid" style="margin: 10px">
          <div class="col">
            <Button
                class="add-button p-button-text"
                label="ADD FOOD"
                @click="addFood"
            />
          </div>
          <div class="col">
            <Button class="add-button p-button-text" label="ADD EXERCISE"/>
          </div>
          <div class="col">
            <Button class="add-button p-button-text" label="ADD NOTES"/>
          </div>
        </div>
        <div class="grid">
          <div class="col">
            <DailyFood ref="dailyFoodRef"></DailyFood>
          </div>
        </div>
        <div class="grid">
          <div class="col-6">
            <TargetsKnob></TargetsKnob>
          </div>
          <div class="col-6">
            <CaloriesBurned></CaloriesBurned>
          </div>
        </div>
        <div class="grid">
          <div class="col">
            <ScoreVitamins kind="VITAMINS"></ScoreVitamins>
          </div>
          <div class="col">
            <ScoreVitamins kind="MINERALS"></ScoreVitamins>
          </div>
        </div>
      </div>
    </div>
  </div>
  <AddFood ref="addFoodDialog" :plan="plan" :day="day"></AddFood>
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
import NavBar from "../components/NavBar.vue";
import TargetsKnob from "../components/TargetsKnob.vue";
import CaloriesBurned from "../components/CaloriesBurned.vue";
import ScoreVitamins from "../components/ScoreVitamins.vue";
import AddFood from "../components/AddFood.vue";
import DailyFood from "../components/DailyFood.vue";
import PlanService from "@/services/PlanService";

export default {
  name: "Plan",
  components: {
    NavBar,
    TargetsKnob,
    CaloriesBurned,
    ScoreVitamins,
    AddFood,
    DailyFood
  },
  data() {
    return {
      nutrient: {
        function: "",
        source: "",
      },
      plan: {
        userEmail: '',
        tags: [],
        notes: []
      },
      day: 1,
      options: {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
      },
      nutrientsDialogVisible: false,
    };
  },
  mounted() {
    const plan = this.$route.params.planId || "";
    PlanService.getPlanByDay(plan, this.day).then((plan) => {
      this.plan = plan[0];
      console.log(this.plan);

      this.$refs.dailyFoodRef.plan = this.plan;
      this.$refs.dailyFoodRef.day = this.day;
      console.log("UPDATE DATA FROM PLAN")
      this.$refs.dailyFoodRef.updateData();
    });
  },
  methods: {
    nextDay() {
      this.day += 1;
      this.$refs.dailyFoodRed.day = this.day;
    },
    previousDay() {
      this.day -= 1;
      this.$refs.dailyFoodRed.day = this.day;
    },
    openDialog(data) {
      this.nutrient = data;
      this.nutrientsDialogVisible = true;
    },
    addFood() {
      this.$refs.addFoodDialog.visible = true;
    },
  }
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