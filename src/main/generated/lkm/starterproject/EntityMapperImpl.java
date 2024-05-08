package lkm.starterproject;

import javax.annotation.processing.Generated;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-08T16:21:59+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
public class EntityMapperImpl implements EntityMapper {

    @Override
    public Company toEntity(CompanyDto companyDto) {
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

    @Override
    public Address toEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.code( addressDto.getCode() );
        address.postcode( addressDto.getPostcode() );
        address.roadAddress( addressDto.getRoadAddress() );
        address.jibunAddress( addressDto.getJibunAddress() );
        address.extraAddress( addressDto.getExtraAddress() );
        address.guideText( addressDto.getGuideText() );

        return address.build();
    }

    @Override
    public LocalTax toEntity(LocalTaxDto localTaxDto) {
        if ( localTaxDto == null ) {
            return null;
        }

        LocalTax.LocalTaxBuilder localTax = LocalTax.builder();

        localTax.code( localTaxDto.getCode() );
        localTax.name( localTaxDto.getName() );

        return localTax.build();
    }

    @Override
    public TaxOffice toEntity(TaxOfficeDto taxOfficeDto) {
        if ( taxOfficeDto == null ) {
            return null;
        }

        TaxOffice.TaxOfficeBuilder taxOffice = TaxOffice.builder();

        taxOffice.code( taxOfficeDto.getCode() );
        taxOffice.name( taxOfficeDto.getName() );
        taxOffice.jurisdiction( taxOfficeDto.getJurisdiction() );

        return taxOffice.build();
    }

    @Override
    public CompanyDto fromEntity(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setNameOfRepresentative( company.getName() );
        companyDto.setCode( company.getCode() );
        companyDto.setLicenseType( company.getLicenseType() );
        companyDto.setHeadOfficeStatus( company.getHeadOfficeStatus() );
        companyDto.setPaymentHeadOfficeStatus( company.getPaymentHeadOfficeStatus() );
        companyDto.setName( company.getName() );
        companyDto.setBusinessRegistrationNumber( company.getBusinessRegistrationNumber() );
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
}
