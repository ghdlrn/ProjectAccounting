package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Compendium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompendiumRepository extends JpaRepository<Compendium, Long> {
    List<Compendium> findByAccountTitleId(Long accountTitleId);
}
