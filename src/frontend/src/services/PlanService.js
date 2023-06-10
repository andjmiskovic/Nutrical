import { instance, fetch } from './http.js'

const getPlan = planId => {
    return fetch(instance.get(`/get-plan/${planId}`, configuration))
}

const getPlanByDay = (planId, day) => {
    return fetch(instance.get(`/get-plan/${planId}/${day}`, configuration))
}

const addFood = addFoodRequest => {
    return fetch(instance.post('/add-food', addFoodRequest, configuration))
}

const removeFood = removeFoodRequest => {
    return fetch(instance.post('/remove-food', removeFoodRequest, configuration))
}

const addTag = addTagRequest => {
    return fetch(instance.post('/add-tag', addTagRequest, configuration))
}

const renameTag = renameTagRequest => {
    return fetch(instance.post('/rename-tag', renameTagRequest, configuration))
}

const removeTag = removeTagRequest => {
    return fetch(instance.post('/remove-tag', removeTagRequest, configuration))
}

const configuration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    }
}

export default {
    getPlan,
    getPlanByDay,
    addFood,
    removeFood,
    addTag,
    renameTag,
    removeTag
}