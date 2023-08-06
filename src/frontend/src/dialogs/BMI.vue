<template>
  <Dialog v-model:visible="visibility" style="width: 90%">
    <h3>BMI (Body Mass Index)</h3>
    <p>BMI is a person's weight in kilograms (or pounds) divided by the square of height in meters (or feet). A high BMI
      can indicate high body fatness. BMI screens for weight categories that may lead to health problems, but it does
      not diagnose the body fatness or health of an individual.</p>
    <img src="../assets/images/bmi.jpg" style="width: 100%" alt="BMI table">
  </Dialog>
</template>

<script>
export default {
  name: 'BMIDialog',
  props: ['display'],
  data() {
    return {
      visibility: this.display
    }
  }
}
</script>