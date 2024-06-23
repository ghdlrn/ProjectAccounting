package lkm.starterproject.accounting.mapper.document;

import lkm.starterproject.accounting.dto.document.PurchaseAndSalesDocumentDto;
import lkm.starterproject.accounting.entity.document.PurchaseAndSalesDocument;
import lkm.starterproject.accounting.mapper.register.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AccountTitleMapper.class, CompendiumMapper.class,
                CustomerMapper.class, FinanceMapper.class, CardMapper.class})
public interface PurchaseAndSalesDocumentMapper {

    PurchaseAndSalesDocumentDto toDto(PurchaseAndSalesDocument entity);

    @Mapping(target = "company", ignore = true)
    PurchaseAndSalesDocument toEntity(PurchaseAndSalesDocumentDto dto);

    List<PurchaseAndSalesDocumentDto> toDtoList(List<PurchaseAndSalesDocument> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(PurchaseAndSalesDocumentDto dto, @MappingTarget PurchaseAndSalesDocument entity);
}
