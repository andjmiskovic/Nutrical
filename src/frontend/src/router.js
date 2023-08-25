import HomePage from "@/pages/HomePage";
import {createRouter, createWebHistory} from 'vue-router'
import RegistrationPage from "@/pages/RegistrationPage";
import VerificationPage from "@/pages/VerificationPage";
import Plan from "@/pages/Plan";
import Clients from "@/pages/Clients";

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
            path: '/plan/:planId',
            component: Plan
        },
        {
            path: '/clients',
            component: Clients
        }
    ]
});

export default router