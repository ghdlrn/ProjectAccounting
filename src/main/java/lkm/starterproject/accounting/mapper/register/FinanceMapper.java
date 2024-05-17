package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { CompanyMapper.class, LocalTaxMapper.class })
public interface FinanceMapper {

    @Mapping(source = "localTax", target = "localTax")
    @Mapping(source = "company", target = "company")
    FinanceDto toDto(Finance entity);

    @Mapping(source = "localTax", target = "localTax")
    @Mapping(source = "company", target = "company")
    Finance toEntity(FinanceDto dto);

    void updateDto(FinanceDto dto, @MappingTarget Finance entity);
}
