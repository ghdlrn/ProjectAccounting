package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, LocalTaxMapper.class})
public interface FinanceMapper {

    FinanceDto toDto(Finance entity);

    Finance toEntity(FinanceDto dto);

    List<FinanceDto> toDtoList(List<Finance> entityList);

    void updateDto(FinanceDto dto, @MappingTarget Finance entity);
}
