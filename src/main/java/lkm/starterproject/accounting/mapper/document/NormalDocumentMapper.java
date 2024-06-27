package lkm.starterproject.accounting.mapper.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.entity.document.NormalDocument;
import lkm.starterproject.accounting.mapper.register.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AccountTitleMapper.class, CompendiumMapper.class,
                CustomerMapper.class, FinanceMapper.class, CardMapper.class})
public interface NormalDocumentMapper {

    NormalDocumentDto toDto(NormalDocument entity);

    @Mapping(target = "company", ignore = true)
    NormalDocument toEntity(NormalDocumentDto dto);

    List<NormalDocumentDto> toDtoList(List<NormalDocument> entityList);

    List<NormalDocument> toEntityList(List<NormalDocumentDto> dtoList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(NormalDocumentDto dto, @MappingTarget NormalDocument entity);

}
