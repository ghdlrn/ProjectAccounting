import { defineStore } from 'pinia';
import type {Company} from "~/types/accounting/company";
import {useNuxtApp} from "#app";

interface CompanyMember {
    email: string;
    role: string;
}

export const useCompanyStore = defineStore('company', {
    state: () => ({
        companies: [] as Company[],
        currentCompany: null as Company | null,  // 현재 선택된 회사 정보
        selectedCompany: null as Company | null,  // 현재 선택된 회사 정보
    }),
    actions: {
        async fetchCompanies() {
            try {
                const response =  await useNuxtApp().$api.get('/register/company');
                this.companies = response.data;
            } catch (error: any) {
                console.error('회사목록 조회 실패:', error.message);
                throw new Error('회사목록 조회 실패');
            }
        },
        async getCompany(id: number) {
            try {
                const response =  await useNuxtApp().$api.get(`/register/company/${id}`);
                this.currentCompany = response.data;  // 현재 회사 정보를 currentCompany에 저장
            } catch (error: any) {
                console.error('회사정보 조회 실패:', error.message);
                throw new Error('회사정보 조회 실패');
            }
        },
        async createCompany(companyData: Partial<Company>) {
            try {
                const response =  await useNuxtApp().$api.post('/register/company', companyData);
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
                const response =  await useNuxtApp().$api.put(`/register/company/${data.id}`, data);
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
                const response =  await useNuxtApp().$api.delete(`/register/company/${id}`);
                if (response.status === 200) {
                    await this.fetchCompanies();
                    alert('회사 정보가 삭제되었습니다');
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
        },

        async selectCompany(companyId: number) {
            this.selectedCompany = this.companies.find(company => company.id === companyId) || null;
             await useNuxtApp().$api.post('/api/company/select', {companyId});
        },

        async assignRole(companyId: number, email: string, role: string) {
            try {
                 await useNuxtApp().$api.post(`/api/company/${companyId}/assign-role`, { email, role });
            } catch (error) {
                // Handle error
            }
        },
    }
});
