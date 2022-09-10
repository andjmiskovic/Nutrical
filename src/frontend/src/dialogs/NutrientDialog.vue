<template>
    <Dialog v-model:visible="visibility">
        <h3>Functions</h3>
        <p>{{nutrient.function}}</p>
        <h3>Sources</h3>
        <p>{{nutrient.sources}}</p>
    </Dialog>
</template>

<script>
export default {
    name: 'NutrientDialog',
    props: ['nutrient', 'display'],
    data() {
        return {
            visibility: this.display
        }
    },
    mounted() {
        /*fetch("/api/calculator/getFood")
      .then((response) => response.text())
      .then((data) => {
        this.nutrients = JSON.parse(data);
      });*/
    }
}
</script>

<style>

</style>