package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCompanyId(Long companyId);

    Optional<Card> findByIdAndCompanyId(Long id, Long companyId);
}
