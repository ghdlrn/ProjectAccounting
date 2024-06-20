package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, TaxOfficeMapper.class, LocalTaxMapper.class})
public interface CompanyMapper {

    @Mapping(target = "currentCompany", ignore = true)
    @Mapping(target = "memberCompanyCode", ignore = true)
    CompanyDto toDto(Company entity);

    @Mapping(target = "memberCompanies", ignore = true)
    @Mapping(target = "customers", ignore = true)
    @Mapping(target = "finances", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "accountTitles", ignore = true)
    @Mapping(target = "normalDocuments", ignore = true)
    Company toEntity(CompanyDto dto);

    List<CompanyDto> toDtoList(List<Company> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "memberCompanies", ignore = true)
    @Mapping(target = "customers", ignore = true)
    @Mapping(target = "finances", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "accountTitles", ignore = true)
    @Mapping(target = "normalDocuments", ignore = true)
    void updateDto(CompanyDto dto, @MappingTarget Company entity);

    default CompanyDto toDtoWithCurrentCompany(Company entity, boolean currentCompany) {
        CompanyDto dto = toDto(entity);
        dto.setCurrentCompany(currentCompany);
        return dto;
    }
}
