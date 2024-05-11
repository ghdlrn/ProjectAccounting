import { defineStore } from 'pinia';
import axios from 'axios';

interface TaxOffice {
    code: number;
    name: string;
    jurisdiction: string;
}

interface LocalTax {
    code: number;
    name: string;
}

interface Company {
    code: number;
    licenseType: string;
    headOfficeStatus: string;
    paymentHeadOfficeStatus: string;
    name: string;
    businessRegistrationNumber: string;
    nameOfRepresentative: string;
    corporationRegistrationNumber: string;
    postcode: string;   //우편번호
    roadAddress: string; //도로명주소
    jibunAddress: string;    //지번주소
    extraAddress: string;    //상세주소
    guideText: string;   //참고사항
    businessType: string;
    businessItem: string;
    fiscalYearClass: number;
    fiscalYearStart: Date;
    fiscalYearEnd: Date;
    privatePracticeDate: Date;
    taxOffice: TaxOffice;
    localTax: LocalTax;
    finance: string;
    corporationClassifyStatus: string;
    companyTypeStatus: string;
    businessScaleStatus: string;
    companyStatus: string;
    localTaxBillDivision: string;
    residentRegistrationNumber: string;
    phone: string;
    fax: string;
    chargeName: string;
    chargeEmail: string;
    note: string;
}

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
        async getCompany(code: number) {
            try {
                const response = await axios.get(`http://localhost:8080/register/company/${code}`);
                this.currentCompany = response.data;  // 현재 회사 정보를 currentCompany에 저장
            } catch (error: any) {
                console.error('회사정보 조회 실패:', error.message);
                throw new Error('회사정보 조회 실패');
            }
        },
        setCompany(data) {
            this.currentCompany = data;
        },
        async createCompany(data: Company) {
            try {
                const response = await axios.post('http://localhost:8080/register/company', data);
                this.companies.push(response.data);
            } catch (error: any) {
                console.error('회사등록 실패:', error.message);
                throw new Error('회사등록 실패');
            }
        },
        async updateCompany(data: Company) {
            try {
                const response = await axios.put(`http://localhost:8080/register/company/${data.code}`, data);
                const index = this.companies.findIndex(company => company.code === data.code);
                if (index !== -1) {
                    this.companies[index] = response.data;
                } else {
                    this.companies.push(response.data); // 새로운 데이터가 배열에 없다면 추가
                }
            } catch (error: any) {
                console.error('회사 정보 수정 실패:', error.message);
                throw new Error('회사 정보 수정 실패');
            }
        },

        async deleteCompany(code: number) {
            try {
                const response = await axios.delete(`http://localhost:8080/register/company/${code}`);
                if (response.status === 200) {
                    // 데이터 삭제 성공 후 목록 새로고침
                    await this.fetchCompanies();  // 삭제 후 전체 목록을 다시 불러옴
                } else {
                    console.error('회사 정보 삭제 실패:', response.status);
                    throw new Error('회사 정보 삭제 실패: 서버 응답 ' + response.status);
                }
            } catch (error: any) {
                console.error('회사 정보 삭제 실패:', error.message);
                throw new Error('회사 정보 삭제 실패');
            }
        }

    }
});
