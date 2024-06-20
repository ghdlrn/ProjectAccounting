package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, LocalTaxMapper.class})
public interface FinanceMapper {

    FinanceDto toDto(Finance entity);

    @Mapping(target = "company", ignore = true)
    Finance toEntity(FinanceDto dto);

    List<FinanceDto> toDtoList(List<Finance> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(FinanceDto dto, @MappingTarget Finance entity);
}
