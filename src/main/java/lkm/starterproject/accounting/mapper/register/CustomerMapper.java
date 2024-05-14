package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        CompanyMapper.class, LocalTaxMapper.class
})
public interface CustomerMapper {

    CustomerDto toDto(Customer entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "company", source = "dto.company")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "registrationNumberType", source = "dto.registrationNumberType")
    @Mapping(target = "registrationNumber", source = "dto.registrationNumber")
    @Mapping(target = "nameOfRepresentative", source = "dto.nameOfRepresentative")
    @Mapping(target = "useStatus", source = "dto.useStatus")
    @Mapping(target = "businessType", source = "dto.businessType")
    @Mapping(target = "businessItem", source = "dto.businessItem")
    @Mapping(target = "postcode", source = "dto.postcode")
    @Mapping(target = "roadAddress", source = "dto.roadAddress")
    @Mapping(target = "jibunAddress", source = "dto.jibunAddress")
    @Mapping(target = "extraAddress", source = "dto.extraAddress")
    @Mapping(target = "guideText", source = "dto.guideText")
    @Mapping(target = "phone", source = "dto.phone")
    @Mapping(target = "fax", source = "dto.fax")
    @Mapping(target = "unitReportingCustomerCode", source = "dto.unitReportingCustomerCode")
    @Mapping(target = "taxRegistrationCode", source = "dto.taxRegistrationCode")
    @Mapping(target = "tradeStartDate", source = "dto.tradeStartDate")
    @Mapping(target = "tradeEndDate", source = "dto.tradeEndDate")
    @Mapping(target = "note", source = "dto.note")
    @Mapping(target = "customerChargeDepartment", source = "dto.customerChargeDepartment")
    @Mapping(target = "customerChargePosition", source = "dto.customerChargePosition")
    @Mapping(target = "customerChargeName", source = "dto.customerChargeName")
    @Mapping(target = "customerChargePhone", source = "dto.customerChargePhone")
    @Mapping(target = "customerChargeEmail", source = "dto.customerChargeEmail")
    @Mapping(target = "homePage", source = "dto.homePage")
    @Mapping(target = "customerAccountNumber", source = "dto.customerAccountNumber")
    @Mapping(target = "customerAccountHolder", source = "dto.customerAccountHolder")
    @Mapping(target = "bankLine", source = "dto.bankLine")
    @Mapping(target = "amountOfCollateral", source = "dto.amountOfCollateral")
    @Mapping(target = "liquorRetailStatus", source = "dto.liquorRetailStatus")
    @Mapping(target = "localTax", source = "dto.localTax")
    Customer toEntity(CustomerDto dto);

    @Mapping(target = "company", ignore = true)
    void updateEntityFromDto(CustomerDto dto, @MappingTarget Customer entity);
}
