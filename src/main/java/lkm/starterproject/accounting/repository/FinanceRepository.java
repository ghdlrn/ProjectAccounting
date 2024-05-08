package lkm.starterproject.accounting.repository;

import lkm.starterproject.accounting.entity.register.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Long> {

}
