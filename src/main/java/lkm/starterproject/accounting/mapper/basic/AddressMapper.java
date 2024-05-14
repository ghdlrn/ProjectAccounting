package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.entity.basic.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(Address entity);

    Address toEntity(AddressDto dto);
}
