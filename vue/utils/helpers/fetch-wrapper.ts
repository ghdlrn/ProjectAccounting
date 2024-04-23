import { useAuthStore } from '@/stores/auth';

const baseUrl = process.env.NUXT_ENV_API_URL || 'http://localhost:8080';

export const fetchWrapper = {
    get: request('GET'),
    post: request('POST'),
    put: request('PUT'),
    delete: request('DELETE')
};

function request(method: string) {
    return (url: string, body?: object) => {
        /* eslint-disable @typescript-eslint/no-explicit-any */
        const requestOptions: any = {
            method,
            headers: authHeader(url)
        };
        if (body) {
            requestOptions.headers['Content-Type'] = 'application/json';
            requestOptions.body = JSON.stringify(body);
        }
        return fetch(url, requestOptions).then(handleResponse);
    };
}

// helper functions

function authHeader(url: string) {
    // return auth header with jwt if user is logged in and request is to the api url
    const { user } = useAuthStore();
    const isLoggedIn = !!user?.token;
    const isApiUrl = url.startsWith('http://localhost:8080');
    if (isLoggedIn && isApiUrl) {
        return { Authorization: `Bearer ${user.token}` };
    } else {
        return {};
    }
}

function handleResponse(response: Response) {
    const contentType = response.headers.get('Content-Type');
    if (!response.ok) {
        const { user, logout } = useAuthStore();
        if ([401, 403].includes(response.status) && user) {
            logout();
        }
        // Handle non-JSON responses or plain text responses differently
        return response.text().then(text => {
            const data = contentType && contentType.includes('application/json') ? JSON.parse(text) : text;
            const error = (data && data.message) || response.statusText;
            return Promise.reject(new Error(error));
        });
    }

    // Proceed with JSON parsing only if content type is application/json
    return contentType && contentType.includes('application/json')
        ? response.json()
        : response.text();
}
