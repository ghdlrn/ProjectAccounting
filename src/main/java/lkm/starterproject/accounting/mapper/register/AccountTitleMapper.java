package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = { CompanyMapper.class, CompendiumMapper.class })
public interface AccountTitleMapper {

    @Mapping(source = "company", target = "company")
    @Mapping(source = "compendiums", target = "compendiums")
    AccountTitleDto toDto(AccountTitle entity);

    @Mapping(source = "company", target = "company")
    @Mapping(source = "compendiums", target = "compendiums")
    AccountTitle toEntity(AccountTitleDto dto);

    @Mapping(target = "company", source = "dto.company")
    @Mapping(target = "compendiums", source = "dto.compendiums")
    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
