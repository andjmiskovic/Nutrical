import axios from 'axios'

const baseURL = "http://localhost:8085"

const instance = axios.create({
    baseURL,
    withCredentials: true
})

const getAccessToken = () => localStorage.getItem('access-token')
const setAccesToken = token => localStorage.setItem('access-token', token)
const removeAccesToken = () => localStorage.removeItem('access-token')

instance.interceptors.request.use(request => {
    const accesToken = getAccessToken()
    if (accesToken != null) {
        request.headers['Authorization'] = 'Bearer ' + accesToken
    }
    return request
})

const fetch = async (request, onSuccess, onError) => {
    try {
        const response = await request
        onSuccess && onSuccess(response.data)
        return [response.data, null]
    } catch (error) {
        onError && onError(error.response.data)
        return [null, error.response.data]
    }
}


export {
    instance,
    fetch,
    setAccesToken,
    getAccessToken,
    removeAccesToken
}
