package lkm.starterproject.accounting.repository.document;

import lkm.starterproject.accounting.entity.document.NormalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NormalDocumentRepository extends JpaRepository<NormalDocument, Long> {

    List<NormalDocument> findByCompanyId(Long companyId);

    Optional<NormalDocument> findByIdAndCompanyId(Long id, Long companyId);

    List<NormalDocument> findByCompanyIdAndDate(Long companyId, LocalDate date);

    void deleteByCompanyIdAndDateAndCode(Long companyId, LocalDate date, Long code);
}
