package lkm.starterproject.accounting.controller.document;

import jakarta.validation.Valid;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.service.document.NormalDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/register/normal-document")
@RequiredArgsConstructor
public class NormalDocumentController {

    private final NormalDocumentService normalDocumentService;

    @PostMapping
    public ResponseEntity<List<NormalDocumentDto>> createOrUpdateNormalDocument(@Valid @RequestBody List<NormalDocumentDto> normalDocumentDtos) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<NormalDocumentDto> updatedNormalDocuments = normalDocumentService.createOrUpdateNormalDocuments(email, normalDocumentDtos);
        return ResponseEntity.ok(updatedNormalDocuments);
    }

    @GetMapping
    public ResponseEntity<List<NormalDocumentDto>> getAllNormalDocuments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<NormalDocumentDto> normalDocuments = normalDocumentService.getAllNormalDocuments(email);
        return ResponseEntity.ok(normalDocuments);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNormalDocumentsByDate(@RequestParam String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        normalDocumentService.deleteNormalDocumentsByDate(email, LocalDate.parse(date));
        return ResponseEntity.ok().build();
    }
}
