package lkm.starterproject.accounting.mapper.company;

import javax.annotation.processing.Generated;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-08T16:21:59+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto companyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setCode( company.getCode() );
        companyDto.setLicenseType( company.getLicenseType() );
        companyDto.setHeadOfficeStatus( company.getHeadOfficeStatus() );
        companyDto.setPaymentHeadOfficeStatus( company.getPaymentHeadOfficeStatus() );
        companyDto.setName( company.getName() );
        companyDto.setBusinessRegistrationNumber( company.getBusinessRegistrationNumber() );
        companyDto.setNameOfRepresentative( company.getNameOfRepresentative() );
        companyDto.setCorporationRegistrationNumber( company.getCorporationRegistrationNumber() );
        companyDto.setBusinessType( company.getBusinessType() );
        companyDto.setBusinessItem( company.getBusinessItem() );
        companyDto.setFiscalYearClass( company.getFiscalYearClass() );
        companyDto.setFiscalYearStart( company.getFiscalYearStart() );
        companyDto.setFiscalYearEnd( company.getFiscalYearEnd() );
        companyDto.setPrivatePracticeDate( company.getPrivatePracticeDate() );
        companyDto.setFinance( company.getFinance() );
        companyDto.setCorporationClassifyStatus( company.getCorporationClassifyStatus() );
        companyDto.setCompanyTypeStatus( company.getCompanyTypeStatus() );
        companyDto.setBusinessScaleStatus( company.getBusinessScaleStatus() );
        companyDto.setCompanyStatus( company.getCompanyStatus() );
        companyDto.setLocalTaxBillDivision( company.getLocalTaxBillDivision() );
        companyDto.setResidentRegistrationNumber( company.getResidentRegistrationNumber() );
        companyDto.setPhone( company.getPhone() );
        companyDto.setFax( company.getFax() );
        companyDto.setChargeName( company.getChargeName() );
        companyDto.setChargeEmail( company.getChargeEmail() );
        companyDto.setNote( company.getNote() );

        return companyDto;
    }

    @Override
    public Company companyDtoToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.code( companyDto.getCode() );
        company.licenseType( companyDto.getLicenseType() );
        company.headOfficeStatus( companyDto.getHeadOfficeStatus() );
        company.paymentHeadOfficeStatus( companyDto.getPaymentHeadOfficeStatus() );
        company.name( companyDto.getName() );
        company.businessRegistrationNumber( companyDto.getBusinessRegistrationNumber() );
        company.nameOfRepresentative( companyDto.getNameOfRepresentative() );
        company.corporationRegistrationNumber( companyDto.getCorporationRegistrationNumber() );
        company.businessType( companyDto.getBusinessType() );
        company.businessItem( companyDto.getBusinessItem() );
        company.fiscalYearClass( companyDto.getFiscalYearClass() );
        company.fiscalYearStart( companyDto.getFiscalYearStart() );
        company.fiscalYearEnd( companyDto.getFiscalYearEnd() );
        company.privatePracticeDate( companyDto.getPrivatePracticeDate() );
        company.finance( companyDto.getFinance() );
        company.corporationClassifyStatus( companyDto.getCorporationClassifyStatus() );
        company.companyTypeStatus( companyDto.getCompanyTypeStatus() );
        company.businessScaleStatus( companyDto.getBusinessScaleStatus() );
        company.companyStatus( companyDto.getCompanyStatus() );
        company.localTaxBillDivision( companyDto.getLocalTaxBillDivision() );
        company.residentRegistrationNumber( companyDto.getResidentRegistrationNumber() );
        company.phone( companyDto.getPhone() );
        company.fax( companyDto.getFax() );
        company.chargeName( companyDto.getChargeName() );
        company.chargeEmail( companyDto.getChargeEmail() );
        company.note( companyDto.getNote() );

        return company.build();
    }
}
