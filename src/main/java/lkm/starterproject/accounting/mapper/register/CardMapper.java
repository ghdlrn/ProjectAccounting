package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {CompanyMapper.class, FinanceMapper.class, AddressMapper.class})
public interface CardMapper {

    CardDto toDto(Card entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    Card toEntity(CardDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    void updateDto(CardDto dto, @MappingTarget Card entity);
}