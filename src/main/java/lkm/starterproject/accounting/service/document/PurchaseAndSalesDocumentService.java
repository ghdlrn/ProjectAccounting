package lkm.starterproject.accounting.service.document;

import lkm.starterproject.accounting.dto.document.PurchaseAndSalesDocumentDto;

import java.util.List;

public interface PurchaseAndSalesDocumentService {

    PurchaseAndSalesDocumentDto createPurchaseAndSalesDocument(String email, PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto);

    List<PurchaseAndSalesDocumentDto> getAllPurchaseAndSalesDocuments(String email);

    PurchaseAndSalesDocumentDto getPurchaseAndSalesDocument(String email, Long id);

    PurchaseAndSalesDocumentDto updatePurchaseAndSalesDocument(String email, Long id, PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto);

    void deletePurchaseAndSalesDocument(String email, Long id);
}