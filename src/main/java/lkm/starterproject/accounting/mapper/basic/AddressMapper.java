package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.entity.basic.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address entity);

    Address toEntity(AddressDto dto);
}
