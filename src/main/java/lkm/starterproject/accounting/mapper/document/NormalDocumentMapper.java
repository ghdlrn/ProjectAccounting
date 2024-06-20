package lkm.starterproject.accounting.mapper.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.entity.document.NormalDocument;
import lkm.starterproject.accounting.mapper.register.AccountTitleMapper;
import lkm.starterproject.accounting.mapper.register.CompendiumMapper;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AccountTitleMapper.class, CustomerMapper.class, CompendiumMapper.class})
public interface NormalDocumentMapper {

    NormalDocumentDto toDto(NormalDocument entity);

    NormalDocument toEntity(NormalDocumentDto dto);

    List<NormalDocumentDto> toDtoList(List<NormalDocument> entityList);

    void updateDto(NormalDocumentDto dto, @MappingTarget NormalDocument entity);
}
