package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.Compendium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompendiumMapper {

    CompendiumDto toDto(Compendium entity);
    Compendium toEntity(CompendiumDto dto);

    List<CompendiumDto> toDtoList(List<Compendium> entityList);
    List<Compendium> toEntityList(List<CompendiumDto> dtoList);

    void updateDto(CompendiumDto dto, @MappingTarget Compendium entity);
}
