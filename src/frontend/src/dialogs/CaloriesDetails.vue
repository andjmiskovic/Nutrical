<template>
  <Dialog v-model:visible="visibility" style="width: 90%">
    <h3>Maintenance calories calculations</h3>
    <p>1) base = 9.99 * Weight(kg) + 6.25 * Height(cm) - 4.92 * Age</p>
    <p>2) we modify a base a little bit, depending on client's status</p>
    <p>&#9;for man -> we add 5 to the base</p>
    <p>&#9;for woman -> we subtract 161 to the base</p>
    <p>&#9;for pregnant woman -> we add 340 to the base</p>
    <p>&#9;for breastfeeding woman -> we add 400 to the base</p>
    <p>3) Now, we multiply base with coefficient of activity (selected activity status).</p>
    <hr style="width: 100%">
    <p>Recommendations for setting goal calories:</p>
    <p>- To achieve fat loss, consider reducing daily calorie intake by approximately 10-15% below calculated
      maintenance calories. </p>
    <p>- To achieve muscle gain, consider increasing your daily calorie intake by approximately 10-15%. </p>
  </Dialog>
</template>

<script>
export default {
  name: 'CaloriesDetails',
  props: ['display'],
  data() {
    return {
      visibility: this.display
    }
  }
}
</script>