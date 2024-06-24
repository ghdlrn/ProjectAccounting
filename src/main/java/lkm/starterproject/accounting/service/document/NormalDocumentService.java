package lkm.starterproject.accounting.service.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;

import java.util.List;

public interface NormalDocumentService {

    NormalDocumentDto createNormalDocument(String email, NormalDocumentDto normalDocumentDto);

    List<NormalDocumentDto> getAllNormalDocuments(String email);

    NormalDocumentDto getNormalDocument(String email, Long id);

    NormalDocumentDto updateNormalDocument(String email, Long id, NormalDocumentDto normalDocumentDto);

    void deleteNormalDocument(String email, Long id);
}