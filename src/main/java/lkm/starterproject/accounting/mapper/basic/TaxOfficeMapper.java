package lkm.starterproject.accounting.mapper.basic;

import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaxOfficeMapper {

    @Mappings({
            @Mapping(target = "address", ignore = true),
            @Mapping(target = "postCode", ignore = true),
            @Mapping(target = "phone", ignore = true),
            @Mapping(target = "fax", ignore = true),
            @Mapping(target = "accountNumber", ignore = true)
    })
    TaxOffice toEntity(TaxOfficeDto dto);

    TaxOfficeDto toDto(TaxOffice entity);
}