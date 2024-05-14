export interface Customer {
    id: number;     //거래처 코드번호
    name: string;   //거래처명
    registrationNumberType: string;     //등록번호 구분(사업자or주민or외국인 등록번호)
    registrationNumber: string; //사업자등록번호or주민번호or외국인번호 등 등록번호
    nameOfRepresentative: string;    //대표자명

}