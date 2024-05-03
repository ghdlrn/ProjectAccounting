import { defineStore } from 'pinia';
import { useAddressStore }  from "~/stores/address.js";
import  axios from 'axios';

export const useCompanyStore = defineStore('company', {
    state: () => ({
    }),

    actions: {
        createCompany(data) {
            const addressStore = useAddressStore();
            const companyData ={
                postcode: addressStore.postcode,
                roadAddress: addressStore.roadAddress,
                jibunAddress: addressStore.jibunAddress,
                extraAddress: addressStore.extraAddress,
                guideText: addressStore.guideText,
                // 회사 정보
                companyName: this.companyName,
                registrationNumber: this.registrationNumber,
                taxOffice: this.taxOffice,
                dateEstablished: this.dateEstablished,
            }

        },
        readCompany(id) {

        },
        updateCompany(id, data) {

        },
        deleteCompany(id) {

        }
    }
});