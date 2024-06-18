package lkm.starterproject.accounting.repository.document;

import lkm.starterproject.accounting.entity.document.NormalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NormalDocumentRepository extends JpaRepository<NormalDocument, Long> {

    List<NormalDocument> findByCompanyId(Long companyId);

    Optional<NormalDocument> findByIdAndCompanyId(Long id, Long companyId);
}