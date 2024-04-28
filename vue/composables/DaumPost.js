import { useExternalScript } from '@nuxtjs/composition-api';
import { useAddressStore } from '~/stores/addressStore';

export function useDaumPostcode() {
    const addressStore = useAddressStore();
    useExternalScript('//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js');

    function openPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 로직은 이전 스크립트에서의 로직과 유사하되, 데이터를 Pinia store에 저장
                addressStore.setAddress({
                    zonecode: data.zonecode,
                    roadAddress: data.roadAddress,
                    jibunAddress: data.jibunAddress,
                    extraRoadAddr: data.extraRoadAddr ? ' (' + data.extraRoadAddr + ')' : '',
                    guideText: data.autoRoadAddress ? '(예상 도로명 주소 : ' + data.autoRoadAddress + ')' : (data.autoJibunAddress ? '(예상 지번 주소 : ' + data.autoJibunAddress + ')' : '')
                });
            }
        }).open();
    }

    return { openPostcode };
}
