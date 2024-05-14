import { defineStore } from 'pinia';
import axios from 'axios';
import type {Company} from "~/types/accounting/company";

export const useCompanyStore = defineStore('company', {
    state: () => ({
        companies: [] as Company[],
        currentCompany: null as Company | null,  // 현재 선택된 회사 정보
    }),
    actions: {
        async fetchCompanies() {
            try {
                const response = await axios.get('http://localhost:8080/register/company');
                this.companies = response.data;
            } catch (error: any) {
                console.error('회사목록 조회 실패:', error.message);
                throw new Error('회사목록 조회 실패');
            }
        },
        async getCompany(id: number) {
            try {
                const response = await axios.get(`http://localhost:8080/register/company/${id}`);
                this.currentCompany = response.data;  // 현재 회사 정보를 currentCompany에 저장
            } catch (error: any) {
                console.error('회사정보 조회 실패:', error.message);
                throw new Error('회사정보 조회 실패');
            }
        },
        async createCompany(data: Company) {
            try {
                const response = await axios.post('http://localhost:8080/register/company', data);
                this.companies.push(response.data);
                alert('회사 정보가 등록되었습니다');
            } catch (error: any) {
                alert('회사등록 실패');
                console.error('회사등록 실패:', error.message);
                throw new Error('회사등록 실패');
            }
        },
        async updateCompany(data: Company) {
            try {
                const response = await axios.put(`http://localhost:8080/register/company/${data.id}`, data);
                const index = this.companies.findIndex(company => company.id === data.id);
                if (index !== -1) {
                    this.companies[index] = response.data;
                    alert('회사 정보가 수정되었습니다');
                } else {
                    this.companies.push(response.data); // 새로운 데이터가 배열에 없다면 추가
                    alert('회사 정보가 등록되었습니다');
                }
            } catch (error: any) {
                alert('회사 정보 수정 실패');
                console.error('회사 정보 수정 실패:', error.message);
                throw new Error('회사 정보 수정 실패');
            }
        },

        async deleteCompany(id: number) {
            try {
                const response = await axios.delete(`http://localhost:8080/register/company/${id}`);
                if (response.status === 200) {
                    await this.fetchCompanies();
                    alert('회사 정보 삭제에 성공했습니다');
                } else {
                    alert('회사 정보 삭제에 실패했습니다');
                    console.error('회사 정보 삭제 실패:', response.status);
                    throw new Error('회사 정보 삭제 실패: 서버 응답 ' + response.status);
                }
            } catch (error: any) {
                alert('회사 정보 삭제에 실패했습니다');
                console.error('회사 정보 삭제 실패:', error.message);
                throw new Error('회사 정보 삭제 실패');
            }
        }

    }
});
