package lkm.starterproject.accounting.controller.document;

import jakarta.validation.Valid;

import lkm.starterproject.accounting.dto.document.PurchaseAndSalesDocumentDto;
import lkm.starterproject.accounting.service.document.PurchaseAndSalesDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/purchase-and-sales-document")
@RequiredArgsConstructor
public class PurchaseAndSalesDocumentController {

    private final PurchaseAndSalesDocumentService purchaseAndSalesDocumentService;

    @PostMapping
    public ResponseEntity<PurchaseAndSalesDocumentDto> createPurchaseAndSalesDocument(@Valid @RequestBody PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PurchaseAndSalesDocumentDto createdPurchaseAndSalesDocument = purchaseAndSalesDocumentService.createPurchaseAndSalesDocument(email, purchaseAndSalesDocumentDto);
        return ResponseEntity.ok(createdPurchaseAndSalesDocument);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseAndSalesDocumentDto>> getAllPurchaseAndSalesDocuments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<PurchaseAndSalesDocumentDto> purchaseAndSalesDocuments = purchaseAndSalesDocumentService.getAllPurchaseAndSalesDocuments(email);
        return ResponseEntity.ok(purchaseAndSalesDocuments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseAndSalesDocumentDto> getPurchaseAndSalesDocument(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto = purchaseAndSalesDocumentService.getPurchaseAndSalesDocument(email, id);
        return ResponseEntity.ok(purchaseAndSalesDocumentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseAndSalesDocumentDto> updatePurchaseAndSalesDocument(@PathVariable("id") Long id, @Valid @RequestBody PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PurchaseAndSalesDocumentDto updatedPurchaseAndSalesDocument = purchaseAndSalesDocumentService.updatePurchaseAndSalesDocument(email, id, purchaseAndSalesDocumentDto);
        return ResponseEntity.ok(updatedPurchaseAndSalesDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseAndSalesDocument(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        purchaseAndSalesDocumentService.deletePurchaseAndSalesDocument(email, id);
        return ResponseEntity.ok().build();
    }
}
