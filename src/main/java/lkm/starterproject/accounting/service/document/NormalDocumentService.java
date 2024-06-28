package lkm.starterproject.accounting.service.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;

import java.time.LocalDate;
import java.util.List;

public interface NormalDocumentService {

    List<NormalDocumentDto> createNormalDocuments(String email, List<NormalDocumentDto> normalDocumentDtos);

    List<NormalDocumentDto> getAllNormalDocuments(String email);

    NormalDocumentDto getNormalDocument(String email, Long id);

    NormalDocumentDto updateNormalDocument(String email, Long id, NormalDocumentDto normalDocumentDto);

    void deleteNormalDocument(String email, LocalDate date);
}