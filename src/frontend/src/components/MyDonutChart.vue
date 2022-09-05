<template>
  <div class="svg-item">
    <svg width="100%" height="100%" viewBox="0 0 40 40" class="donut">
      <circle
        class="donut-hole"
        cx="20"
        cy="20"
        r="15.91549430918954"
        fill="#fff"
      ></circle>
      <circle
        class="donut-ring"
        cx="20"
        cy="20"
        r="15.91549430918954"
        fill="transparent"
        stroke-width="3.5"
      ></circle>
      <circle
        class="donut-segment donut-segment-2"
        cx="20"
        cy="20"
        r="15.91549430918954"
        fill="transparent"
        stroke-width="3.5"
        :stroke-dasharray='stroke'
        stroke-dashoffset="25"
      ></circle>
      <g class="donut-text donut-text-1">
        <text y="50%" transform="translate(0, 2)">
          <tspan x="50%" text-anchor="middle" class="donut-percent">{{targetsPercent}}{{measurement}}</tspan>
        </text>
        <text y="60%" transform="translate(0, 2)">
          <tspan x="50%" text-anchor="middle" class="donut-data">
            {{this.text}}
          </tspan>
        </text>
      </g>
    </svg>
  </div>
</template>

<script>
export default {
  props: ['text', 'targetsPercent',  'measurement'],
    mounted() {
        this.stroke = this.targetsPercent + " " + (100 - this.targetsPercent);
    },
    data() {
        return {
            stroke: "0 100",
        }
    }
};
</script>

<style>
.svg-item {
  width: 100%;
  font-size: 16px;
  margin: 0 auto;
  animation: donutfade 1s;
}

@media (min-width: 992px) {
  .svg-item {
    width: 80%;
  }
}

.donut-ring {
  stroke: #ebebeb;
}

.donut-segment {
  transform-origin: center;
}

.donut-segment-2 {
  stroke: var(--green);
  animation: donut1 3s;
}

.segment-1 {
  fill: #ccc;
}
.segment-2 {
  fill: var(--green);
}

.donut-percent {
  animation: donutfadelong 1s;
}

.donut-text {
  font-family: Arial, Helvetica, sans-serif;
}

.donut-text-1 {
  fill: var(--green);
}

.donut-label {
  font-size: 0.28em;
  font-weight: 700;
  line-height: 1;
  fill: #000;
  transform: translateY(0.25em);
}

.donut-percent {
  font-size: 0.5em;
  line-height: 1;
  transform: translateY(0.5em);
  font-weight: bold;
}

.donut-data {
  font-size: 0.15em;
  line-height: 1;
  transform: translateY(0.5em);
  text-align: center;
  text-anchor: middle;
  color: #666;
  fill: #666;
  animation: donutfadelong 1s;
}
</style>