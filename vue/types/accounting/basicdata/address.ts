export interface Address {
    postcode: string;
    roadAddress: string;
    jibunAddress: string;
    extraAddress: string;
    guideText: string;
}

export interface AddressData {
    zonecode: string;
    roadAddress: string;
    jibunAddress: string;
    bname: string;
    buildingName: string;
    apartment: string;
    autoRoadAddress?: string;
    autoJibunAddress?: string;
}