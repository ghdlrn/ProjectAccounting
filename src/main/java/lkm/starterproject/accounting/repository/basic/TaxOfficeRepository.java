package lkm.starterproject.accounting.repository.basic;

import lkm.starterproject.accounting.entity.basic.TaxOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TaxOfficeRepository extends JpaRepository<TaxOffice, Integer>, QuerydslPredicateExecutor<TaxOffice> {
}
