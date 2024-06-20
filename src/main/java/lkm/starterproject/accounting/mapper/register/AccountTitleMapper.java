package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompendiumMapper.class})
public interface AccountTitleMapper {

    AccountTitleDto toDto(AccountTitle entity);

    @Mapping(target = "company", ignore = true)
    AccountTitle toEntity(AccountTitleDto dto);

    List<AccountTitleDto> toDtoList(List<AccountTitle> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
