package lkm.starterproject.accounting.service.impl.document;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.document.PurchaseAndSalesDocumentDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.document.PurchaseAndSalesDocument;
import lkm.starterproject.accounting.mapper.document.PurchaseAndSalesDocumentMapper;
import lkm.starterproject.accounting.repository.document.PurchaseAndSalesDocumentRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.document.PurchaseAndSalesDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseAndSalesDocumentServiceImpl implements PurchaseAndSalesDocumentService {

    private final PurchaseAndSalesDocumentRepository purchaseAndSalesDocumentRepository;
    private final PurchaseAndSalesDocumentMapper purchaseAndSalesDocumentMapper;
    private final CompanyService companyService;

    @Override
    @Transactional
    public PurchaseAndSalesDocumentDto createPurchaseAndSalesDocument(String email, PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto) {
        Company company = companyService.getCurrentCompany(email);
        PurchaseAndSalesDocument purchaseAndSalesDocument = purchaseAndSalesDocumentMapper.toEntity(purchaseAndSalesDocumentDto);
        purchaseAndSalesDocument.setCompany(company);
        purchaseAndSalesDocument = purchaseAndSalesDocumentRepository.save(purchaseAndSalesDocument);
        return purchaseAndSalesDocumentMapper.toDto(purchaseAndSalesDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseAndSalesDocumentDto> getAllPurchaseAndSalesDocuments(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<PurchaseAndSalesDocument> purchaseAndSalesDocuments = purchaseAndSalesDocumentRepository.findByCompanyId(company.getId());
        return purchaseAndSalesDocumentMapper.toDtoList(purchaseAndSalesDocuments);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseAndSalesDocumentDto getPurchaseAndSalesDocument(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        PurchaseAndSalesDocument purchaseAndSalesDocument = purchaseAndSalesDocumentRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("PurchaseAndSalesDocument 정보를 찾을 수 없음"));
        return purchaseAndSalesDocumentMapper.toDto(purchaseAndSalesDocument);
    }

    @Override
    @Transactional
    public PurchaseAndSalesDocumentDto updatePurchaseAndSalesDocument(String email, Long id, PurchaseAndSalesDocumentDto purchaseAndSalesDocumentDto) {
        Company company = companyService.getCurrentCompany(email);
        PurchaseAndSalesDocument purchaseAndSalesDocument = purchaseAndSalesDocumentRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("PurchaseAndSalesDocument 정보를 찾을 수 없음"));
        purchaseAndSalesDocumentMapper.updateDto(purchaseAndSalesDocumentDto, purchaseAndSalesDocument);
        purchaseAndSalesDocument = purchaseAndSalesDocumentRepository.save(purchaseAndSalesDocument);
        return purchaseAndSalesDocumentMapper.toDto(purchaseAndSalesDocument);
    }

    @Override
    @Transactional
    public void deletePurchaseAndSalesDocument(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        PurchaseAndSalesDocument purchaseAndSalesDocument = purchaseAndSalesDocumentRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("PurchaseAndSalesDocument 정보를 찾을 수 없음"));
        purchaseAndSalesDocumentRepository.delete(purchaseAndSalesDocument);
    }
}
