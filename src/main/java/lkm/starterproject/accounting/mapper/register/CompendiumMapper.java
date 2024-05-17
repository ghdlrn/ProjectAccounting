package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.Compendium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AccountTitleMapper.class)
public interface CompendiumMapper {

    CompendiumMapper INSTANCE = Mappers.getMapper(CompendiumMapper.class);

    CompendiumDto toDto(Compendium entity);

    Compendium toEntity(CompendiumDto dto);

    void updateDto(CompendiumDto dto, @MappingTarget Compendium entity);
}
