import {instance, fetch} from './http.js'

const login = body => {
    return fetch(instance.post('/api/auth/login', body, configuration))
}

const logout = body => {
    return fetch(instance.post('/api/auth/logout', body, configuration))
}

const register = body => {
    return fetch(instance.post('/api/auth/register', body, configuration))
}

const verify = body => {
    return fetch(instance.post('/api/auth/register/verify', body, configuration))
}

const getLoggedUserInfo = () => {
    return fetch(instance.get('/api/auth/me', configuration))
}

const configuration = {
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/json',
    }
}

export default {
    login,
    logout,
    register,
    verify,
    getLoggedUserInfo
}