import axios from 'axios';

const baseUrl = '/api/calculator';

const getNutrientsInPlan = (nutrientsRequest) => {
    return axios.post(`${baseUrl}/get-nutrients-in-plan`, nutrientsRequest, configuration);
};

const getNutrientsList = () => {
    return axios.get(`${baseUrl}/get-nutrients`, configuration);
};

const getFood = (search, limit) => {
    return axios.get(`${baseUrl}/get-food`, { params: { search, limit } }, configuration);
};

const getFoodByName = (name) => {
    return axios.get(`${baseUrl}/get-food-by-name`, { params: { name } }, configuration);
};

const getActivityStatuses = () => {
    return axios.get(`${baseUrl}/get-activity-statuses`);
};

const configuration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    }
}

export default {
    getNutrientsInPlan,
    getNutrientsList,
    getFood,
    getFoodByName,
    getActivityStatuses
};
