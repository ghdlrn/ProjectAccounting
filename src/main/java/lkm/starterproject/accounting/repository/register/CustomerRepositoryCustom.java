package lkm.starterproject.accounting.repository.register;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Long> findCodesByCompanyId(Long companyId);
}
