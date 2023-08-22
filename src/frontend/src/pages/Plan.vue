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
      <p style="line-height: 5px">plan id: {{ planId }}</p>
    </div>
    <div class="col-3">
      <Button icon="pi pi-send" label="Send" style="margin-top: 20px; float: right; margin-right: 20px" @click="send">
      </Button>
      <Button icon="pi pi-download" label="Download" style="margin-top: 20px; float: right; margin-right: 20px" @click="generatePlan">
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
        <div class="number-list">
          <div
              v-for="number in plan.daysInPlan"
              :key="number"
              class="number-item"
              :class="{ selected: number === day }"
              @click="selectDay(number)"
          >
            {{ number }}
          </div>
        </div>
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
        <Button v-if="!trainingVisible" class="add-button p-button-text" @click="trainingVisible = true"
                label="ADD EXERCISE"/>
        <Button v-if="trainingVisible" class="add-button p-button-text" @click="trainingVisible = false"
                label="REMOVE EXERCISE"/>
      </div>
      <div class="col">
        <Button v-if="!notesVisible" class="add-button p-button-text" @click="notesVisible = true"
                label="ADD NOTES"/>
        <Button v-if="notesVisible" class="add-button p-button-text" @click="notesVisible = false"
                label="REMOVE NOTES"/>
      </div>
    </div>
    <div class="grid">
      <div :class="notesVisible ? 'col-6' : 'col-12'" v-if="trainingVisible">
        <Training ref="training" :plan-id="plan.id" :day="day"></Training>
      </div>
      <div :class="trainingVisible ? 'col-6' : 'col-12'" v-if="notesVisible">
        <Notes ref="notes" :plan-id="plan.id" :day="day"></Notes>
      </div>
    </div>
    <div class="grid">
      <div class="col">
        <DailyFood ref="dailyFoodRef" :daily-plan-id="plan.id"></DailyFood>
      </div>
    </div>
    <div class="grid">
      <div class="col-6">
        <MyDonutChart ref="donutChart"></MyDonutChart>
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

  <AddMeal ref="addMealDialog" :plan="plan" :day="day" :meal-name="''"></AddMeal>
  <SendPlanDialog ref="sendPlanDialog" :plan="plan"></SendPlanDialog>

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
import SendPlanDialog from "../dialogs/SendPlanDialog.vue";
import PlanService from "@/services/PlanService";
import NutrientService from "@/services/NutrientService";
import Training from "../components/Training";
import Notes from "../components/Notes";
import MyDonutChart from "@/components/MyDonutChart";

export default {
  name: "Plan",
  components: {
    MyDonutChart,
    NavBar,
    Notes,
    CaloriesBurned,
    ScoreVitamins,
    DailyFood,
    Training,
    AddMeal,
    SendPlanDialog
  },
  data() {
    return {
      nutrient: {
        function: "",
        source: "",
      },
      notesVisible: true,
      trainingVisible: true,
      plan: {
        userEmail: '',
        meals: [],
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
        console.log(plan[0]);
        this.plan = plan[0];
        this.$refs.dailyFoodRef.plan = this.plan;
        this.$refs.dailyFoodRef.day = this.day;
        this.$refs.dailyFoodRef.updateData();
        this.$refs.notes.notes = this.plan.notes;
        this.$refs.training.trainingPlan = this.plan.training;
        if (this.plan.training === '') {
          this.$refs.training.restDay = "Rest day";
        }
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
        this.$refs.donutChart.updateValues(nutrients.data);
      })
    },
    nextDay() {
      this.selectDay(this.day + 1);
    },
    previousDay() {
      this.selectDay(this.day - 1);
    },
    selectDay(n) {
      this.day = n;
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
    },
    send() {
      this.$refs.sendPlanDialog.visible = true;
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

.grid {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.col-fixed {
  flex: 0 0 auto;
}

.col {
  flex: 1 1 auto;
}

.number-list {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.number-item {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  margin: 0 5px;
  cursor: pointer;
  background-color: #eee;
  transition: background-color 0.3s;
}

.number-item:hover {
  background-color: #ccc;
}

.number-item.selected {
  background-color: var(--green);
  color: white;
}
</style>