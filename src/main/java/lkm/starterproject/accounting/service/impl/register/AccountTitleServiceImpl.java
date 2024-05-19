package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.mapper.register.AccountTitleMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.register.AccountTitleRepository;
import lkm.starterproject.accounting.service.register.AccountTitleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountTitleServiceImpl implements AccountTitleService {

    private final AccountTitleRepository accountTitleRepository;
    private final AccountTitleMapper accountTitleMapper;
    private final CompanyRepository companyRepository;

    public AccountTitleServiceImpl(AccountTitleRepository accountTitleRepository, CompanyRepository companyRepository, AccountTitleMapper accountTitleMapper) {
        this.accountTitleRepository = accountTitleRepository;
        this.accountTitleMapper = accountTitleMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public AccountTitleDto createAccountTitle(Long companyId, AccountTitleDto accountTitleDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        AccountTitle accountTitle = accountTitleMapper.toEntity(accountTitleDto);
        accountTitle.setCompany(company);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountTitleDto> getAllAccountTitles(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        List<AccountTitle> accountTitles = accountTitleRepository.findByCompanyId(companyId);
        return accountTitleMapper.toDtoList(accountTitles);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountTitleDto getAccountTitle(Long id) {
        AccountTitle accountTitle = accountTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional
    public AccountTitleDto updateAccountTitle(Long id, AccountTitleDto accountTitleDto) {
        AccountTitle accountTitle = accountTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        accountTitleMapper.updateDto(accountTitleDto, accountTitle);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional
    public void deleteAccountTitle(Long id) {
        AccountTitle accountTitle = accountTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AAccountTitle 정보를 찾을 수 없음"));
        accountTitleRepository.delete(accountTitle);
    }
}
