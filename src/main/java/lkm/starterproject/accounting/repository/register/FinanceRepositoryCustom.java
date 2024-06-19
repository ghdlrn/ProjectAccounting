package lkm.starterproject.accounting.repository.register;

import java.util.List;

public interface FinanceRepositoryCustom {
    List<Long> findCodesByCompanyId(Long companyId);
}
