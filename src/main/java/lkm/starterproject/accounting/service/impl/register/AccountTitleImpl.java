package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.repository.register.AccountTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTitleImpl {

    private final AccountTitleRepository accountTitleRepository;
    private final AccountTitleMapper accountTitleMapper;

    @Autowired
    public AccountTitleServiceImpl(AccountTitleRepository accountTitleRepository, AccountTitleMapper accountTitleMapper) {
        this.accountTitleRepository = accountTitleRepository;
        this.accountTitleMapper = accountTitleMapper;
    }

    @Transactional
    public AccountTitleDto createAccountTitle(AccountTitleDto accountTitleDto) {
        AccountTitle accountTitle = accountTitleMapper.toEntity(accountTitleDto);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Transactional(readOnly = true)
    public List<AccountTitleDto> getAllAccountTitles() {
        return accountTitleRepository.findAll().stream()
                .map(accountTitleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AccountTitleDto getAccountTitle(Long id) {
        return accountTitleRepository.findById(id)
                .map(accountTitleMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
    }

    @Transactional
    public AccountTitleDto updateAccountTitle(Long id, AccountTitleDto accountTitleDto) {
        AccountTitle accountTitle = accountTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        accountTitleMapper.updateDto(accountTitleDto, accountTitle);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Transactional
    public void deleteAccountTitle(Long id) {
        AccountTitle accountTitle = accountTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        accountTitleRepository.delete(accountTitle);
    }
}
