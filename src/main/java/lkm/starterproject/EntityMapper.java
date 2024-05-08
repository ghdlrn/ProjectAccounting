package lkm.starterproject;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    Company toEntity(CompanyDto companyDto);

    Address toEntity(AddressDto addressDto);

    LocalTax toEntity(LocalTaxDto localTaxDto);

    TaxOffice toEntity(TaxOfficeDto taxOfficeDto);

    // DTO to Entity conversions with complex mappings can be added here
    // For example:
    @Mapping(source = "name", target = "nameOfRepresentative")
    CompanyDto fromEntity(Company company);
}
