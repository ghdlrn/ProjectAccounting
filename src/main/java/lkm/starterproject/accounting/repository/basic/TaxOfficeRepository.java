package lkm.starterproject.accounting.repository.basic;

import lkm.starterproject.accounting.entity.basic.TaxOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxOfficeRepository extends JpaRepository<TaxOffice, Long> {
}
