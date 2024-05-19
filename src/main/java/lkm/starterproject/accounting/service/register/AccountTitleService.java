package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;

import java.util.List;

public interface AccountTitleService {

    AccountTitleDto createAccountTitle(Long companyId, AccountTitleDto accountTitleDto);

    List<AccountTitleDto> getAllAccountTitles(Long companyId);

    AccountTitleDto getAccountTitle(Long id);

    AccountTitleDto updateAccountTitle(Long id, AccountTitleDto accountTitleDto);

    void deleteAccountTitle(Long id);
}
