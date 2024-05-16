package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = { CompanyMapper.class, FinanceMapper.class, Address.class })
public interface CardMapper {

    @Mapping(source = "company", target = "company")
    @Mapping(source = "finance", target = "finance")
    @Mapping(source = "address", target = "address")
    CardDto toDto(Card entity);

    @Mapping(source = "company", target = "company")
    @Mapping(source = "finance", target = "finance")
    @Mapping(source = "address", target = "address")
    Card toEntity(CardDto dto);

    void updateEntityFromDto(CardDto dto, @MappingTarget Card entity);
}
