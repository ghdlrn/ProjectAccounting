package lkm.starterproject.accounting.repository.register;

import java.util.List;

public interface CardRepositoryCustom {
    List<Long> findCodesByCompanyId(Long companyId);
}
