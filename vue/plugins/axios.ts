// plugins/axios.js 또는 어디에서든 설정 파일을 만듭니다.

import axios from 'axios';

// API 요청을 위한 기본 설정을 가진 axios 인스턴스를 생성합니다.
const apiClient = axios.create({
    baseURL: 'http://localhost:8080', // 백엔드 서버의 URL과 포트를 정확하게 지정합니다.
    headers: {
        'Content-Type': 'application/json'
    }
});

export default apiClient;
