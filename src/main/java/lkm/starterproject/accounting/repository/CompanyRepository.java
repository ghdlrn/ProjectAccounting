package lkm.starterproject.accounting.repository;

import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

}
