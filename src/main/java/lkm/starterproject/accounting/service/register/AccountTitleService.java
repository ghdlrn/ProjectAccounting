package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;

import java.util.List;

public interface AccountTitleService {

    AccountTitleDto createAccountTitle(AccountTitleDto accountTitleDto);

    List<AccountTitleDto> getAllAccountTitles();

    AccountTitleDto getAccountTitle(Long id);

    AccountTitleDto updateAccountTitle(Long id, AccountTitleDto accountTitleDto);

    void deleteAccountTitle(Long id);
}
