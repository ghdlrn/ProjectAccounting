// utils/fetch-wrapper.ts

import { useNuxtApp } from '#app';

export const fetchWrapper = {
    get: (url: string) => {
        const nuxtApp = useNuxtApp();
        return nuxtApp.$api.get(url).then(handleResponse);
    },
    post: (url: string, body: object) => {
        const nuxtApp = useNuxtApp();
        return nuxtApp.$api.post(url, body).then(handleResponse);
    },
    put: (url: string, body: object) => {
        const nuxtApp = useNuxtApp();
        return nuxtApp.$api.put(url, body).then(handleResponse);
    },
    delete: (url: string) => {
        const nuxtApp = useNuxtApp();
        return nuxtApp.$api.delete(url).then(handleResponse);
    }
};

function handleResponse(response: any) {
    return response.data;
}
