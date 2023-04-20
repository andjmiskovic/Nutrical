import axios from 'axios';

const API_URL = '/auth';

class AuthService {

    // login(email, password) {
    //     fetch("/api/auth/login",
    //         {
    //             method: 'POST',
    //             body: JSON.stringify({email, password})
    //         }
    //     )
    //         .then((response) => console.log(response));
    // }

    // static async login(email, password) {
    //     const response = await fetch('/api/auth/login', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({ email, password })
    //     });
    //     const data = await response.json();
    //     if (response.ok) {
    //         localStorage.setItem('token', data.token);
    //         return true;
    //     } else {
    //         throw new Error(data.message || 'Failed to login');
    //     }
    // }

    login(email, password) {
        let body = {
            "email": email,
            "password": password
        }
        return axios.post(`/api/auth/login`, body, this.getHttpOptions());
    }

    logout() {
        return axios.post(`${API_URL}/logout`);
    }

    register(registrationRequest) {
        return axios.post(`${API_URL}/register`, registrationRequest);
    }

    verify(verificationRequest) {
        return axios.post(`${API_URL}/register/verify`, verificationRequest);
    }

    getLoggedUserInfo() {
        return axios.get(`${API_URL}/me`);
    }

    getHttpOptions(params = {}) {
        return {
            headers: {
                'Authorization': localStorage.getItem('token') || 'authkey',
                'Content-Type': 'application/json',
            },
            params: params,
        };
    }
}

export default new AuthService();