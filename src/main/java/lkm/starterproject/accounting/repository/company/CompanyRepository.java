package lkm.starterproject.accounting.repository.company;

import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends
        JpaRepository<Company, Long>,
        QuerydslPredicateExecutor<Company>,
        CompanyRepositoryCustom {
}
