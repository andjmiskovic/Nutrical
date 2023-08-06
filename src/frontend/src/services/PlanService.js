import {instance, fetch} from './http.js'

const getPlan = planId => {
    return fetch(instance.get(`/api/dairy/get-plan/${planId}`, configuration))
}

const getPlanByDay = (planId, day) => {
    return fetch(instance.get(`/api/dairy/get-plan/${planId}/${day}`, configuration))
}

const addFood = addFoodRequest => {
    return fetch(instance.post('/api/dairy/add-food', addFoodRequest, configuration))
}

const removeFood = removeFoodRequest => {
    return fetch(instance.post('/api/dairy/remove-food', removeFoodRequest, configuration))
}

const addTag = addTagRequest => {
    return fetch(instance.post('/api/dairy/add-tag', addTagRequest, configuration))
}

const saveTraining = addTrainingRequest => {
    return fetch(instance.post('/api/dairy/add-training', addTrainingRequest, configuration))
}

const saveNotes = addTrainingRequest => {
    return fetch(instance.post('/api/dairy/add-notes', addTrainingRequest, configuration))
}

const renameTag = renameTagRequest => {
    return fetch(instance.post('/api/dairy/rename-tag', renameTagRequest, configuration))
}

const removeTag = removeTagRequest => {
    return fetch(instance.post('/api/dairy/remove-tag', removeTagRequest, configuration))
}

const generatePlan = planId => {
    return fetch(instance.get('/api/dairy/generate-plan/' + planId, downloadConfiguration))
}

const configuration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    }
}

const downloadConfiguration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    },
    responseType: 'blob',
}

export default {
    getPlan,
    getPlanByDay,
    addFood,
    removeFood,
    addTag,
    renameTag,
    removeTag,
    generatePlan,
    saveTraining,
    saveNotes
}