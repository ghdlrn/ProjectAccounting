package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MemberCompanyMapper.class)
public interface CardMapper {

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    CardDto toDto(Card entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    Card toEntity(CardDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CardDto> toDTOList(List<Card> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<Card> toEntityList(List<CardDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장

    void updateDto(CardDto dto, @MappingTarget Card entity);
}
