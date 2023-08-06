<template>
  <NavBar active-page="dashboard"></NavBar>
  <img
      class="dashboard-background"
      src="../assets/images/dashboard-background.jpg"
      alt=""
  />
  <div class="grid" style="margin-left: 20px; margin-top: -80px;">
    <div class="welcome col-9" style="float: left">
      <p style="font-size: 30px">Client: {{ plan.name }} {{ plan.surname }} {{ plan.userEmail }}</p>
      <p style="line-height: 5px">plan id: {{ plan.id }}</p>
    </div>
    <div class="col-3">
      <Button style="margin-top: 20px; float: right; margin-right: 20px" @click="generatePlan">Generate plan PDF
      </Button>
    </div>
  </div>
  <div style="width: 70%; margin: auto;">
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
            label="ADD MEAL"
            @click="addMeal"
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
        <DailyFood ref="dailyFoodRef" :daily-plan-id="plan.id"></DailyFood>
      </div>
    </div>
    <div class="grid">
      <div class="col-6">
<!--        <TargetsKnob ref="targets"></TargetsKnob>-->
      </div>
      <div class="col-6">
        <CaloriesBurned ref="calories"></CaloriesBurned>
      </div>
    </div>
    <div class="grid">
      <div class="col">
        <ScoreVitamins ref="carbs" kind="CARBOHYDRATES"></ScoreVitamins>
        <ScoreVitamins ref="lipids" kind="LIPIDS"></ScoreVitamins>
        <ScoreVitamins ref="proteins" kind="PROTEIN"></ScoreVitamins>
      </div>
      <div class="col">
        <ScoreVitamins ref="vitamins" kind="VITAMINS"></ScoreVitamins>
        <ScoreVitamins ref="minerals" kind="MINERALS"></ScoreVitamins>
      </div>
    </div>
  </div>
  <AddMeal ref="addMealDialog" :plan="plan" :day="day" :tag-name="''"></AddMeal>
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
import CaloriesBurned from "../components/MacronutrientsTarget.vue";
import ScoreVitamins from "../components/ScoreVitamins.vue";
import DailyFood from "../components/DailyFood.vue";
import AddMeal from "../dialogs/AddMeal.vue";
import PlanService from "@/services/PlanService";
import NutrientService from "@/services/NutrientService";

export default {
  name: "Plan",
  components: {
    NavBar,
    CaloriesBurned,
    ScoreVitamins,
    DailyFood,
    AddMeal
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
      planId: 0,
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
    this.planId = this.$route.params.planId || "";
    this.reloadPlan();
  },
  methods: {
    reloadPlan() {
      PlanService.getPlanByDay(this.planId, this.day).then((plan) => {
        this.plan = plan[0];
        this.$refs.dailyFoodRef.plan = this.plan;
        this.$refs.dailyFoodRef.day = this.day;
        this.$refs.dailyFoodRef.updateData();
        this.getNutrients();
      });
    },
    getNutrients() {
      let body = {
        "planId": this.planId,
        "day": this.day
      }
      NutrientService.getNutrientsInPlan(body).then((nutrients) => {
        console.log(nutrients.data);
        this.$refs.calories.updateValues(nutrients.data, nutrients.data.calories, nutrients.data.caloriesGoal);
        this.$refs.vitamins.updateValues(nutrients.data.nutrientsScore);
        this.$refs.minerals.updateValues(nutrients.data.nutrientsScore);
        this.$refs.proteins.updateValues(nutrients.data.nutrientsScore);
        this.$refs.lipids.updateValues(nutrients.data.nutrientsScore);
        this.$refs.carbs.updateValues(nutrients.data.nutrientsScore);
      })
    },
    nextDay() {
      this.day += 1;
      this.$refs.dailyFoodRef.day = this.day;
      this.reloadPlan();
    },
    previousDay() {
      this.day -= 1;
      this.$refs.dailyFoodRef.day = this.day;
      this.reloadPlan();
    },
    openDialog(data) {
      this.nutrient = data;
      this.nutrientsDialogVisible = true;
    },
    addMeal() {
      this.$refs.addMealDialog.visible = true;
    },
    generatePlan() {
      PlanService.generatePlan(this.planId).then((content) => {
        console.log(content)
        const element = document.createElement('a');
        const file = new Blob([content[0]], {type: 'application/pdf'});
        element.href = URL.createObjectURL(file);
        element.download = "report.pdf";
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
      });
    }
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
  color: white;
  text-shadow: 2px 2px 10px black;
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