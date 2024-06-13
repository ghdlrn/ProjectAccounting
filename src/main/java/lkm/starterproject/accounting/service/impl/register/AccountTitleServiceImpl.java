package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.mapper.register.AccountTitleMapper;
import lkm.starterproject.accounting.repository.register.AccountTitleRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.register.AccountTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTitleServiceImpl implements AccountTitleService {

    private final AccountTitleRepository accountTitleRepository;
    private final AccountTitleMapper accountTitleMapper;
    private final CompanyService companyService;

    @Override
    @Transactional
    public AccountTitleDto createAccountTitle(String email, AccountTitleDto accountTitleDto) {
        Company company = companyService.getCurrentCompany(email);
        AccountTitle accountTitle = accountTitleMapper.toEntity(accountTitleDto);
        accountTitle.setCompany(company);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountTitleDto> getAllAccountTitles(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<AccountTitle> accountTitles = accountTitleRepository.findByCompanyId(company.getId());
        return accountTitleMapper.toDtoList(accountTitles);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountTitleDto getAccountTitle(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        AccountTitle accountTitle = accountTitleRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional
    public AccountTitleDto updateAccountTitle(String email, Long id, AccountTitleDto accountTitleDto) {
        Company company = companyService.getCurrentCompany(email);
        AccountTitle accountTitle = accountTitleRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        accountTitleMapper.updateDto(accountTitleDto, accountTitle);
        accountTitle = accountTitleRepository.save(accountTitle);
        return accountTitleMapper.toDto(accountTitle);
    }

    @Override
    @Transactional
    public void deleteAccountTitle(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        AccountTitle accountTitle = accountTitleRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("AAccountTitle 정보를 찾을 수 없음"));
        accountTitleRepository.delete(accountTitle);
    }
}
