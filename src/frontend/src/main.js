import { createApp } from 'vue';
import App from './App.vue';
import '../src/assets/style.css'

import PrimeVue from 'primevue/config';
import Button from 'primevue/button';

import 'primevue/resources/themes/saga-blue/theme.css' //theme
import 'primevue/resources/primevue.min.css' //core css
import 'primeicons/primeicons.css' //icons

const app = createApp(App);
app.use(PrimeVue);

app.component('MyButton', Button);

app.mount('#app');