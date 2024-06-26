package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.register.FinanceMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.register.FinanceRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.register.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository financeRepository;
    private final FinanceMapper financeMapper;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyService companyService;

    @Override
    @Transactional
    public FinanceDto createFinance(String email, FinanceDto financeDto) {
        Company company = companyService.getCurrentCompany(email);
        Finance finance = financeMapper.toEntity(financeDto);
        assignLocalTaxAndTaxOffice(finance, financeDto);
        finance.setCompany(company);
        finance.setCode(generateFinanceCode(company.getId()));
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinanceDto> getAllFinances(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<Finance> finances = financeRepository.findByCompanyId(company.getId());
        return financeMapper.toDtoList(finances);
    }

    @Override
    @Transactional(readOnly = true)
    public FinanceDto getFinance(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Finance finance = financeRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional
    public FinanceDto updateFinance(String email, Long id, FinanceDto financeDto) {
        Company company = companyService.getCurrentCompany(email);
        Finance finance = financeRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        assignLocalTaxAndTaxOffice(finance, financeDto);
        financeMapper.updateDto(financeDto, finance);
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional
    public void deleteFinance(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Finance finance = financeRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        financeRepository.delete(finance);
    }

    private void assignLocalTaxAndTaxOffice(Finance finance, FinanceDto financeDto) {
        if (financeDto.getLocalTax() != null && financeDto.getLocalTax().getId() != null) {
            finance.setLocalTax(findLocalTax(financeDto.getLocalTax().getId()));
        } else {
            finance.setLocalTax(null);
        }
    }   //LocalTax null허용

    private LocalTax findLocalTax(Long id) {
        return id == null ? null : localTaxRepository.findById(id).orElse(null);
    }

    private Long generateFinanceCode(Long companyId) {
        List<Long> existingCodes = financeRepository.findCodesByCompanyId(companyId);
        Long code = 50001L;
        for (Long existingCode : existingCodes) {
            if (!existingCode.equals(code)) {break;}
            code++;
        }
        return code;
    }
}
