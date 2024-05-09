package lkm.starterproject.accounting.mapper.company;

import javax.annotation.processing.Generated;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-09T12:03:22+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto toDto(Company company) {
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
        companyDto.setPostcode( company.getPostcode() );
        companyDto.setRoadAddress( company.getRoadAddress() );
        companyDto.setJibunAddress( company.getJibunAddress() );
        companyDto.setExtraAddress( company.getExtraAddress() );
        companyDto.setGuideText( company.getGuideText() );
        companyDto.setBusinessType( company.getBusinessType() );
        companyDto.setBusinessItem( company.getBusinessItem() );
        companyDto.setFiscalYearClass( company.getFiscalYearClass() );
        companyDto.setFiscalYearStart( company.getFiscalYearStart() );
        companyDto.setFiscalYearEnd( company.getFiscalYearEnd() );
        companyDto.setPrivatePracticeDate( company.getPrivatePracticeDate() );
        companyDto.setTaxOffice( taxOfficeToTaxOfficeDto( company.getTaxOffice() ) );
        companyDto.setLocalTax( localTaxToLocalTaxDto( company.getLocalTax() ) );
        companyDto.setFinance( company.getFinance() );
        companyDto.setCorporationClassifyStatus( company.getCorporationClassifyStatus() );
        companyDto.setCompanyTypeStatus( company.getCompanyTypeStatus() );
        companyDto.setBusinessScaleStatus( company.getBusinessScaleStatus() );
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
        company.postcode( companyDto.getPostcode() );
        company.roadAddress( companyDto.getRoadAddress() );
        company.jibunAddress( companyDto.getJibunAddress() );
        company.extraAddress( companyDto.getExtraAddress() );
        company.guideText( companyDto.getGuideText() );
        company.businessType( companyDto.getBusinessType() );
        company.businessItem( companyDto.getBusinessItem() );
        company.fiscalYearClass( companyDto.getFiscalYearClass() );
        company.fiscalYearStart( companyDto.getFiscalYearStart() );
        company.fiscalYearEnd( companyDto.getFiscalYearEnd() );
        company.privatePracticeDate( companyDto.getPrivatePracticeDate() );
        company.taxOffice( taxOfficeDtoToTaxOffice( companyDto.getTaxOffice() ) );
        company.localTax( localTaxDtoToLocalTax( companyDto.getLocalTax() ) );
        company.finance( companyDto.getFinance() );
        company.corporationClassifyStatus( companyDto.getCorporationClassifyStatus() );
        company.companyTypeStatus( companyDto.getCompanyTypeStatus() );
        company.businessScaleStatus( companyDto.getBusinessScaleStatus() );
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
    public void updateEntity(CompanyDto companyDto, Company company) {
        if ( companyDto == null ) {
            return;
        }

        company.setCode( companyDto.getCode() );
        company.setLicenseType( companyDto.getLicenseType() );
        company.setHeadOfficeStatus( companyDto.getHeadOfficeStatus() );
        company.setPaymentHeadOfficeStatus( companyDto.getPaymentHeadOfficeStatus() );
        company.setName( companyDto.getName() );
        company.setBusinessRegistrationNumber( companyDto.getBusinessRegistrationNumber() );
        company.setNameOfRepresentative( companyDto.getNameOfRepresentative() );
        company.setCorporationRegistrationNumber( companyDto.getCorporationRegistrationNumber() );
        company.setPostcode( companyDto.getPostcode() );
        company.setRoadAddress( companyDto.getRoadAddress() );
        company.setJibunAddress( companyDto.getJibunAddress() );
        company.setExtraAddress( companyDto.getExtraAddress() );
        company.setGuideText( companyDto.getGuideText() );
        company.setBusinessType( companyDto.getBusinessType() );
        company.setBusinessItem( companyDto.getBusinessItem() );
        company.setFiscalYearClass( companyDto.getFiscalYearClass() );
        company.setFiscalYearStart( companyDto.getFiscalYearStart() );
        company.setFiscalYearEnd( companyDto.getFiscalYearEnd() );
        company.setPrivatePracticeDate( companyDto.getPrivatePracticeDate() );
        if ( companyDto.getTaxOffice() != null ) {
            if ( company.getTaxOffice() == null ) {
                company.setTaxOffice( TaxOffice.builder().build() );
            }
            taxOfficeDtoToTaxOffice1( companyDto.getTaxOffice(), company.getTaxOffice() );
        }
        else {
            company.setTaxOffice( null );
        }
        if ( companyDto.getLocalTax() != null ) {
            if ( company.getLocalTax() == null ) {
                company.setLocalTax( LocalTax.builder().build() );
            }
            localTaxDtoToLocalTax1( companyDto.getLocalTax(), company.getLocalTax() );
        }
        else {
            company.setLocalTax( null );
        }
        company.setFinance( companyDto.getFinance() );
        company.setCorporationClassifyStatus( companyDto.getCorporationClassifyStatus() );
        company.setCompanyTypeStatus( companyDto.getCompanyTypeStatus() );
        company.setBusinessScaleStatus( companyDto.getBusinessScaleStatus() );
        company.setLocalTaxBillDivision( companyDto.getLocalTaxBillDivision() );
        company.setResidentRegistrationNumber( companyDto.getResidentRegistrationNumber() );
        company.setPhone( companyDto.getPhone() );
        company.setFax( companyDto.getFax() );
        company.setChargeName( companyDto.getChargeName() );
        company.setChargeEmail( companyDto.getChargeEmail() );
        company.setNote( companyDto.getNote() );
    }

    protected TaxOfficeDto taxOfficeToTaxOfficeDto(TaxOffice taxOffice) {
        if ( taxOffice == null ) {
            return null;
        }

        Long code = null;
        String name = null;
        String jurisdiction = null;

        code = taxOffice.getCode();
        name = taxOffice.getName();
        jurisdiction = taxOffice.getJurisdiction();

        TaxOfficeDto taxOfficeDto = new TaxOfficeDto( code, name, jurisdiction );

        return taxOfficeDto;
    }

    protected LocalTaxDto localTaxToLocalTaxDto(LocalTax localTax) {
        if ( localTax == null ) {
            return null;
        }

        Long code = null;
        String name = null;

        code = localTax.getCode();
        name = localTax.getName();

        LocalTaxDto localTaxDto = new LocalTaxDto( code, name );

        return localTaxDto;
    }

    protected TaxOffice taxOfficeDtoToTaxOffice(TaxOfficeDto taxOfficeDto) {
        if ( taxOfficeDto == null ) {
            return null;
        }

        TaxOffice.TaxOfficeBuilder taxOffice = TaxOffice.builder();

        taxOffice.code( taxOfficeDto.getCode() );
        taxOffice.name( taxOfficeDto.getName() );
        taxOffice.jurisdiction( taxOfficeDto.getJurisdiction() );

        return taxOffice.build();
    }

    protected LocalTax localTaxDtoToLocalTax(LocalTaxDto localTaxDto) {
        if ( localTaxDto == null ) {
            return null;
        }

        LocalTax.LocalTaxBuilder localTax = LocalTax.builder();

        localTax.code( localTaxDto.getCode() );
        localTax.name( localTaxDto.getName() );

        return localTax.build();
    }

    protected void taxOfficeDtoToTaxOffice1(TaxOfficeDto taxOfficeDto, TaxOffice mappingTarget) {
        if ( taxOfficeDto == null ) {
            return;
        }

        mappingTarget.setCode( taxOfficeDto.getCode() );
        mappingTarget.setName( taxOfficeDto.getName() );
        mappingTarget.setJurisdiction( taxOfficeDto.getJurisdiction() );
    }

    protected void localTaxDtoToLocalTax1(LocalTaxDto localTaxDto, LocalTax mappingTarget) {
        if ( localTaxDto == null ) {
            return;
        }

        mappingTarget.setCode( localTaxDto.getCode() );
        mappingTarget.setName( localTaxDto.getName() );
    }
}
