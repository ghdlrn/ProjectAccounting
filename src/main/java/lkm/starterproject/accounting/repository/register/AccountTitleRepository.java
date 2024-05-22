package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.AccountTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTitleRepository extends JpaRepository<AccountTitle, Long> {
    List<AccountTitle> findByCompanyId(Long companyId);

    Optional<AccountTitle> findByIdAndCompanyId(Long id, Long companyId);
}
