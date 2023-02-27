export default class NutrientService {

    nutrients;
    food;

    static loadFood() {
        fetch("/api/calculator/get-food")
            .then((response) => response.text())
            .then((data) => {
                this.food = JSON.parse(data);
            });
    }

    static loadNutrients() {
        fetch("/api/calculator/get-nutrients")
            .then((response) => response.text())
            .then((data) => {
                this.nutrients = JSON.parse(data);
                for (var i = 0; i < this.nutrients.length; i++) {
                    this.data.push({
                        nutrient: this.nutrients[i].name,
                        function: this.nutrients[i].function,
                        sources: this.nutrients[i].sources,
                        quantity: 0,
                        unit: this.nutrients[i].unit,
                        progress: 0,
                    });
                }
            });
    }

    static getNutrients() {
        return this.nutrients;
    }

    static getFood() {
        return this.food;
    }

    static getNutrientsByKind(kind) {
        fetch("/api/calculator/get-nutrients")
            .then((response) => response.text())
            .then((data) => {
                this.nutrients = JSON.parse(data);
                return this.nutrients.filter(obj => {
                    return obj.kind === kind;
                });
            });
    }

    static getNutrientData(kind) {
        fetch("/api/calculator/get-nutrients")
            .then((response) => response.text())
            .then((data) => {
                var n = JSON.parse(data).filter((obj) => {
                    return obj.kind === kind;
                });
                console.log(n)
                var nutrientData = [];
                for (var i = 0; i < n.length; i++) {
                    nutrientData.push({
                        nutrient: n[i].name,
                        function: n[i].function,
                        sources: n[i].sources,
                        quantity: 0,
                        unit: n[i].unit,
                        progress: 0,
                    });
                }
                return nutrientData;
            });
    }
}