package lkm.starterproject.accounting.repository.basic;

import lkm.starterproject.accounting.entity.basic.TaxOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxOfficeRepository extends JpaRepository<TaxOffice, Integer> {
    Optional<TaxOffice> findByName(String name);
}
