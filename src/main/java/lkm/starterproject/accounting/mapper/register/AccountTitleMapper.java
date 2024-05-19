package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = CompanyMapper.class)
public interface AccountTitleMapper {

    AccountTitleDto toDto(AccountTitle entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    AccountTitle toEntity(AccountTitleDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
