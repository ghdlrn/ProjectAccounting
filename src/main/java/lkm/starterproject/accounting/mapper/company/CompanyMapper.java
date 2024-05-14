package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        TaxOfficeMapper.class, LocalTaxMapper.class, MemberCompanyMapper.class, CustomerMapper.class
})
public interface CompanyMapper {

    CompanyDto toDto(Company entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "licenseType", source = "dto.licenseType")
    @Mapping(target = "headOfficeStatus", source = "dto.headOfficeStatus")
    @Mapping(target = "paymentHeadOfficeStatus", source = "dto.paymentHeadOfficeStatus")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "businessRegistrationNumber", source = "dto.businessRegistrationNumber")
    @Mapping(target = "nameOfRepresentative", source = "dto.nameOfRepresentative")
    @Mapping(target = "corporationRegistrationNumber", source = "dto.corporationRegistrationNumber")
    @Mapping(target = "postcode", source = "dto.postcode")
    @Mapping(target = "roadAddress", source = "dto.roadAddress")
    @Mapping(target = "jibunAddress", source = "dto.jibunAddress")
    @Mapping(target = "extraAddress", source = "dto.extraAddress")
    @Mapping(target = "guideText", source = "dto.guideText")
    @Mapping(target = "businessType", source = "dto.businessType")
    @Mapping(target = "businessItem", source = "dto.businessItem")
    @Mapping(target = "fiscalYearClass", source = "dto.fiscalYearClass")
    @Mapping(target = "fiscalYearStart", source = "dto.fiscalYearStart")
    @Mapping(target = "fiscalYearEnd", source = "dto.fiscalYearEnd")
    @Mapping(target = "privatePracticeDate", source = "dto.privatePracticeDate")
    @Mapping(target = "taxOffice", source = "dto.taxOffice")
    @Mapping(target = "localTax", source = "dto.localTax")
    @Mapping(target = "finance", source = "dto.finance")
    @Mapping(target = "corporationClassifyStatus", source = "dto.corporationClassifyStatus")
    @Mapping(target = "companyTypeStatus", source = "dto.companyTypeStatus")
    @Mapping(target = "businessScaleStatus", source = "dto.businessScaleStatus")
    @Mapping(target = "localTaxBillDivision", source = "dto.localTaxBillDivision")
    @Mapping(target = "residentRegistrationNumber", source = "dto.residentRegistrationNumber")
    @Mapping(target = "phone", source = "dto.phone")
    @Mapping(target = "fax", source = "dto.fax")
    @Mapping(target = "chargeName", source = "dto.chargeName")
    @Mapping(target = "chargeEmail", source = "dto.chargeEmail")
    @Mapping(target = "note", source = "dto.note")
    @Mapping(target = "memberCompanies", source = "dto.memberCompanies")
    @Mapping(target = "customers", source = "dto.customers")
    Company toEntity(CompanyDto dto);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "customers", ignore = true)
    void updateEntityFromDto(CompanyDto dto, @MappingTarget Company entity);
}
