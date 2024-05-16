package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {
}
