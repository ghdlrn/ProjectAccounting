import axios from 'axios';
import { useRuntimeConfig } from '#app';

const apiClient = () => {
    const config = useRuntimeConfig();
    const apiBaseUrl = config.public.apiBaseUrl;
    return axios.create({
        baseURL: apiBaseUrl,
    });
};

export default apiClient;
