package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.Compendium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = AccountTitleMapper.class)
public interface CompendiumMapper {

    CompendiumDto toDto(Compendium entity);

    Compendium toEntity(CompendiumDto dto);

    void updateDto(CompendiumDto dto, @MappingTarget Compendium entity);
}
