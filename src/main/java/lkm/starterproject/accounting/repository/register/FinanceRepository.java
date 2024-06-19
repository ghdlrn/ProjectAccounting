package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>, FinanceRepositoryCustom {
    List<Finance> findByCompanyId(Long companyId);

    Optional<Finance> findByIdAndCompanyId(Long id, Long companyId);
}
