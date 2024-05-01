package lkm.starterproject.accounting.repository.basic;

import lkm.starterproject.accounting.entity.basic.LocalTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LocalTaxRepository extends JpaRepository<LocalTax, Long>, QuerydslPredicateExecutor<LocalTax> {

}
