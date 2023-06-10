import Dashboard from './pages/Dashboard.vue'
import HomePage from "@/pages/HomePage";
import {createRouter, createWebHistory} from 'vue-router'
import RegistrationPage from "@/pages/RegistrationPage";
import VerificationPage from "@/pages/VerificationPage";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: HomePage
        },
        {
            path: '/registration',
            component: RegistrationPage
        },
        {
            path: '/registration/verification',
            component: VerificationPage
        },
        {
            path: '/dashboard',
            component: Dashboard
        }
    ]
});

export default router