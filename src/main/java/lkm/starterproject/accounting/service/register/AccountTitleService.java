package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;

import java.util.List;

public interface AccountTitleService {

    AccountTitleDto createAccountTitle(String email, AccountTitleDto accountTitleDto);

    List<AccountTitleDto> getAllAccountTitles(String email);

    AccountTitleDto getAccountTitle(String email, Long id);

    AccountTitleDto updateAccountTitle(String email, Long id, AccountTitleDto accountTitleDto);

    void deleteAccountTitle(String email, Long id);
}
