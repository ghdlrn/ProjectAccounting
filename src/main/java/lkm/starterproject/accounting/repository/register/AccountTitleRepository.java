package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.AccountTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTitleRepository extends JpaRepository<AccountTitle, Long> {
}
