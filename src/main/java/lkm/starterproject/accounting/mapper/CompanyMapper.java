package lkm.starterproject.accounting.mapper;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CompanyMapper {


    @Mapping(target = "taxOffice", source = "taxOffice")
    @Mapping(target = "localTax", source = "localTax")
    CompanyDto toDto(Company company);

    @Mapping(target = "taxOffice", source = "taxOffice")
    @Mapping(target = "localTax", source = "localTax")
    Company toEntity(CompanyDto companyDto);

    @Mapping(target = "taxOffice", source = "taxOffice")
    @Mapping(target = "localTax", source = "localTax")
    void updateEntityFromDto(CompanyDto dto, @MappingTarget Company company);
}
