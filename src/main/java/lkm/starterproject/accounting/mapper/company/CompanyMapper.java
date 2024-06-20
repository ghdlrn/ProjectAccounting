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

    Company toEntity(CompanyDto dto);

    List<CompanyDto> toDtoList(List<Company> entityList);

    void updateDto(CompanyDto dto, @MappingTarget Company entity);

    default CompanyDto toDtoWithCurrentCompany(Company entity, boolean currentCompany) {
        CompanyDto dto = toDto(entity);
        dto.setCurrentCompany(currentCompany);
        return dto;
    }
}
