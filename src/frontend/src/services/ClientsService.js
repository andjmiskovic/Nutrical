import { instance, fetch } from './http.js'

const addClient = (clientRequest) => {
    return fetch(instance.post('/api/nutritionist/add-client', clientRequest, configuration))
}

const getClients = (nutritionistEmail) => {
    return fetch(instance.get(`/api/nutritionist/get-clients/${nutritionistEmail}`, configuration))
}

const getClient = (nutritionistEmail, clientEmail) => {
    return fetch(instance.get(`/api/nutritionist/get-client/${nutritionistEmail}/${clientEmail}`, configuration))
}

const configuration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    }
}

export default {
    addClient,
    getClients,
    getClient
}
