package lkm.starterproject.accounting.service.document;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface NormalDocumentService {

    List<NormalDocumentDto> getAllNormalDocuments(String email);

    List<NormalDocumentDto> createOrUpdateNormalDocuments(String email, List<NormalDocumentDto> normalDocumentDto);

    void deleteNormalDocumentsByDate(String email, LocalDate date);
}
