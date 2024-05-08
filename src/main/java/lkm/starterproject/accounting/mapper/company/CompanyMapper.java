package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
    void updateEntity(CompanyDto companyDto, @MappingTarget Company company);
}
