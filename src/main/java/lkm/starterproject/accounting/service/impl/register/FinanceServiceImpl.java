package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.register.FinanceMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.register.FinanceRepository;
import lkm.starterproject.accounting.service.register.FinanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository financeRepository;
    private final FinanceMapper financeMapper;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyRepository companyRepository;

    public FinanceServiceImpl(FinanceRepository financeRepository, FinanceMapper financeMapper,
                              LocalTaxRepository localTaxRepository, CompanyRepository companyRepository) {
        this.financeRepository = financeRepository;
        this.financeMapper = financeMapper;
        this.localTaxRepository = localTaxRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public FinanceDto createFinance(Long companyId, FinanceDto financeDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        Finance finance = financeMapper.toEntity(financeDto);
        assignLocalTaxAndTaxOffice(finance, financeDto);
        finance.setCompany(company);
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinanceDto> getAllFinances(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        List<Finance> finances = financeRepository.findByCompanyId(companyId);
        return financeMapper.toDtoList(finances);
    }

    @Override
    @Transactional(readOnly = true)
    public FinanceDto getFinance(Long id) {
        Finance finance = financeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional
    public FinanceDto updateFinance(Long id, FinanceDto financeDto) {
        Finance finance = financeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        assignLocalTaxAndTaxOffice(finance, financeDto);
        financeMapper.updateDto(financeDto, finance);
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

    @Override
    @Transactional
    public void deleteFinance(Long id) {
        Finance finance = financeRepository.findById(id)
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
}
