package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompendiumMapper.class})
public interface AccountTitleMapper {

    AccountTitleDto toDto(AccountTitle entity);

    AccountTitle toEntity(AccountTitleDto dto);

    List<AccountTitleDto> toDtoList(List<AccountTitle> entityList);

    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
