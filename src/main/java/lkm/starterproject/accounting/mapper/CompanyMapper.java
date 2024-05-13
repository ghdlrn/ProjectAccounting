package lkm.starterproject.accounting.mapper;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {TaxOfficeMapper.class, LocalTaxMapper.class,  MemberCompanyMapper.class})
public interface CompanyMapper {

    CompanyDto toDto(Company company);

    @Mapping(target = "code", source = "companyDto.code")
    @Mapping(target = "licenseType", source = "companyDto.licenseType")
    @Mapping(target = "headOfficeStatus", source = "companyDto.headOfficeStatus")
    @Mapping(target = "paymentHeadOfficeStatus", source = "companyDto.paymentHeadOfficeStatus")
    @Mapping(target = "name", source = "companyDto.name")
    @Mapping(target = "businessRegistrationNumber", source = "companyDto.businessRegistrationNumber")
    @Mapping(target = "nameOfRepresentative", source = "companyDto.nameOfRepresentative")
    @Mapping(target = "corporationRegistrationNumber", source = "companyDto.corporationRegistrationNumber")
    @Mapping(target = "postcode", source = "companyDto.postcode")
    @Mapping(target = "roadAddress", source = "companyDto.roadAddress")
    @Mapping(target = "jibunAddress", source = "companyDto.jibunAddress")
    @Mapping(target = "extraAddress", source = "companyDto.extraAddress")
    @Mapping(target = "guideText", source = "companyDto.guideText")
    @Mapping(target = "businessType", source = "companyDto.businessType")
    @Mapping(target = "businessItem", source = "companyDto.businessItem")
    @Mapping(target = "fiscalYearClass", source = "companyDto.fiscalYearClass")
    @Mapping(target = "fiscalYearStart", source = "companyDto.fiscalYearStart")
    @Mapping(target = "fiscalYearEnd", source = "companyDto.fiscalYearEnd")
    @Mapping(target = "privatePracticeDate", source = "companyDto.privatePracticeDate")
    @Mapping(target = "taxOffice", source = "companyDto.taxOffice")
    @Mapping(target = "localTax", source = "companyDto.localTax")
    @Mapping(target = "finance", source = "companyDto.finance")
    @Mapping(target = "corporationClassifyStatus", source = "companyDto.corporationClassifyStatus")
    @Mapping(target = "companyTypeStatus", source = "companyDto.companyTypeStatus")
    @Mapping(target = "businessScaleStatus", source = "companyDto.businessScaleStatus")
    @Mapping(target = "localTaxBillDivision", source = "companyDto.localTaxBillDivision")
    @Mapping(target = "residentRegistrationNumber", source = "companyDto.residentRegistrationNumber")
    @Mapping(target = "phone", source = "companyDto.phone")
    @Mapping(target = "fax", source = "companyDto.fax")
    @Mapping(target = "chargeName", source = "companyDto.chargeName")
    @Mapping(target = "chargeEmail", source = "companyDto.chargeEmail")
    @Mapping(target = "note", source = "companyDto.note")
    @Mapping(target = "memberCompanies", source = "companyDto.memberCompanies")
    Company toEntity(CompanyDto companyDto);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntityFromDto(CompanyDto dto, @MappingTarget Company company);
}

