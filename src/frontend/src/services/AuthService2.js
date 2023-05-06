import { instance, fetch } from './http.js'

const login = body => {
    return fetch(instance.post('/api/auth/login', body, {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': localStorage.getItem('token') || 'authkey',
                'Content-Type': 'application/json',
            }
        })
    )
}

export default {
    login
}