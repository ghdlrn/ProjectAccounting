import { defineStore } from 'pinia';

export const useAddressStore = defineStore('address', {
    state: () => ({
        postcode: '',
        roadAddress: '',
        jibunAddress: '',
        extraAddress: '',
        guideText: ''
    }),
    actions: {
        setAddress(data) {
            this.postcode = data.zonecode;
            this.roadAddress = data.roadAddress;
            this.jibunAddress = data.jibunAddress;
            this.extraAddress = data.extraRoadAddr;
            this.guideText = data.guideText;
        },
        clearAddress() {
            this.postcode = '';
            this.roadAddress = '';
            this.jibunAddress = '';
            this.extraAddress = '';
            this.guideText = '';
        }
    }
});
