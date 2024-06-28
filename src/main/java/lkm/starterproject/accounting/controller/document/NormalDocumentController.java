package lkm.starterproject.accounting.controller.document;

import jakarta.validation.Valid;

import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.service.document.NormalDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<List<NormalDocumentDto>> createNormalDocument(@Valid @RequestBody List<NormalDocumentDto> normalDocumentDtos) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<NormalDocumentDto> createdNormalDocuments = normalDocumentService.createNormalDocuments(email, normalDocumentDtos);
        return ResponseEntity.ok(createdNormalDocuments);
    }

    @GetMapping
    public ResponseEntity<List<NormalDocumentDto>> getAllNormalDocuments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<NormalDocumentDto> normalDocuments = normalDocumentService.getAllNormalDocuments(email);
        return ResponseEntity.ok(normalDocuments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NormalDocumentDto> getNormalDocument(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        NormalDocumentDto normalDocumentDto = normalDocumentService.getNormalDocument(email, id);
        return ResponseEntity.ok(normalDocumentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NormalDocumentDto> updateNormalDocument(@PathVariable("id") Long id, @Valid @RequestBody NormalDocumentDto normalDocumentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        NormalDocumentDto updatedNormalDocument = normalDocumentService.updateNormalDocument(email, id, normalDocumentDto);
        return ResponseEntity.ok(updatedNormalDocument);
    }

    @DeleteMapping("/{date}")
    public ResponseEntity<Void> deleteNormalDocumentsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        normalDocumentService.deleteNormalDocument(email, date);
        return ResponseEntity.ok().build();
    }
}
