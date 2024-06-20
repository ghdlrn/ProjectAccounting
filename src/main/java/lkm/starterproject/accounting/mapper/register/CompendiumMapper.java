package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.Compendium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompendiumMapper {

    CompendiumDto toDto(Compendium entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(target = "accountTitle", ignore = true)
    Compendium toEntity(CompendiumDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CompendiumDto> toDtoList(List<Compendium> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "accountTitle", ignore = true)
    void updateDto(CompendiumDto dto, @MappingTarget Compendium entity);
}
