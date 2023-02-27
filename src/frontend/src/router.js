import Dashboard from './pages/Dashboard.vue'
import HomePage from "@/pages/HomePage";
import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: HomePage
        },
        {
            path: '/dashboard',
            component: Dashboard
        }
    ]
});

export default router