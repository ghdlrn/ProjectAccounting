package lkm.starterproject.accounting.repository.document;

import lkm.starterproject.accounting.entity.document.PurchaseAndSalesDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseAndSalesDocumentRepository extends JpaRepository<PurchaseAndSalesDocument, Long> {

    List<PurchaseAndSalesDocument> findByCompanyId(Long companyId);

    Optional<PurchaseAndSalesDocument> findByIdAndCompanyId(Long id, Long companyId);
}

