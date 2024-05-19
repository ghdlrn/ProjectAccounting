package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTitleMapper {

    AccountTitleDto toDto(AccountTitle entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달
    AccountTitle toEntity(AccountTitleDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<AccountTitleDto> toDtoList(List<AccountTitle> entityList);
    List<AccountTitle> toEntityList(List<AccountTitleDto> dtoList);

    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
