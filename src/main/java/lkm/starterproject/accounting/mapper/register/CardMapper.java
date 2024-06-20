package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FinanceMapper.class, AddressMapper.class})
public interface CardMapper {

    CardDto toDto(Card entity);

    @Mapping(target = "company", ignore = true)
    Card toEntity(CardDto dto);

    List<CardDto> toDtoList(List<Card> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(CardDto dto, @MappingTarget Card entity);
}
