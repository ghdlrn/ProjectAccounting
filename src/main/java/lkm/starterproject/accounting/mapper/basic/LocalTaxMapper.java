package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocalTaxMapper {

    @Mapping(target = "status", ignore = true)
    LocalTax toEntity(LocalTaxDto dto);

    LocalTaxDto toDto(LocalTax entity);
}