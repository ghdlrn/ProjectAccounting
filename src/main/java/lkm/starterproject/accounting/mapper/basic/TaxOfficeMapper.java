package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxOfficeMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "postCode", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "fax", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "jurisdiction", source = "dto.jurisdiction")
    TaxOffice toEntity(TaxOfficeDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "jurisdiction", source = "entity.jurisdiction")
    TaxOfficeDto toDto(TaxOffice entity);
}