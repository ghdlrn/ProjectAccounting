// stores/address.js
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
            this.extraAddress = this.getExtraAddress(data);
            this.guideText = this.getGuideText(data, this.extraAddress);
        },
        updateAddress(data) {
            this.postcode = data.postcode;
            this.roadAddress = data.roadAddress;
            this.jibunAddress = data.jibunAddress;
            this.extraAddress = data.extraAddress;
            this.guideText = data.guideText;
        },
        getExtraAddress(data) {
            let extraAddr = '';
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            return extraAddr !== '' ? `(${extraAddr})` : '';
        },
        getGuideText(data, extraAddr) {
            if (data.autoRoadAddress) {
                return `(예상 도로명 주소 : ${data.autoRoadAddress + extraAddr})`;
            } else if (data.autoJibunAddress) {
                return `(예상 지번 주소 : ${data.autoJibunAddress})`;
            }
            return '';
        }
    }
});
