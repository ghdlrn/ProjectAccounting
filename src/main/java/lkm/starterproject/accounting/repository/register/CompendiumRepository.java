package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Compendium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompendiumRepository extends JpaRepository<Compendium, Long>,
        QuerydslPredicateExecutor<Compendium>, CompendiumRepositoryCustom {
    List<Compendium> findByAccountTitleId(Long accountTitleId);
}
