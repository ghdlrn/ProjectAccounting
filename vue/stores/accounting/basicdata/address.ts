// stores/address.js
import { defineStore } from 'pinia';
import type {Address, AddressData} from "~/types/accounting/basicdata/address";


export const useAddressStore = defineStore('address', {
    state: () => ({
        address: {} as Address,
    }),
    actions: {
        setAddress(data: AddressData) {
            const newAddress: Address = {
            postcode: data.zonecode,
            roadAddress: data.roadAddress,
            jibunAddress: data.jibunAddress,
            extraAddress: this.getExtraAddress(data),
            guideText: this.getGuideText(data, this.getExtraAddress(data))
            };
            this.address = newAddress;
        },
        updateAddress(data: Address) {
            this.address = data;
        },
        getExtraAddress(data: AddressData) {
            let extraAddr = '';
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            return extraAddr !== '' ? `(${extraAddr})` : '';
        },
        getGuideText(data: AddressData, extraAddr: string) {
            if (data.autoRoadAddress) {
                return `(예상 도로명 주소 : ${data.autoRoadAddress + extraAddr})`;
            } else if (data.autoJibunAddress) {
                return `(예상 지번 주소 : ${data.autoJibunAddress})`;
            }
            return '';
        }
    }
});
