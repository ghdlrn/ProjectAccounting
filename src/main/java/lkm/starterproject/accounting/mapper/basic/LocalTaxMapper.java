package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocalTaxMapper {

    LocalTaxMapper INSTANCE = Mappers.getMapper(LocalTaxMapper.class);

    @Mapping(target = "status", ignore = true)
    LocalTax toEntity(LocalTaxDto dto);

    LocalTaxDto toDto(LocalTax entity);
}