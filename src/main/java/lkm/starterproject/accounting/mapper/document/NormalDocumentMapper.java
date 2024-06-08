package lkm.starterproject.accounting.mapper.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.entity.document.NormalDocument;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.mapper.register.AccountTitleMapper;
import lkm.starterproject.accounting.mapper.register.CompendiumMapper;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CompanyMapper.class, AccountTitleMapper.class,
                CustomerMapper.class, CompendiumMapper.class})
public interface NormalDocumentMapper {

    @Mapping(target = "company", ignore = true)
    NormalDocumentDto toDto(NormalDocument entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달
    NormalDocument toEntity(NormalDocumentDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<NormalDocumentDto> toDtoList(List<NormalDocument> entityList);

    void updateDto(NormalDocumentDto dto, @MappingTarget NormalDocument entity);
}
