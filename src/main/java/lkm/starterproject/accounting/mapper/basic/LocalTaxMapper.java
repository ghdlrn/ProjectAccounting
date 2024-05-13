package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocalTaxMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "status", ignore = true)
    LocalTax toEntity(LocalTaxDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    LocalTaxDto toDto(LocalTax entity);
}