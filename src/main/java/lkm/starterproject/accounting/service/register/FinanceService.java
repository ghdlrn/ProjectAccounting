package lkm.starterproject.accounting.service.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.register.FinanceMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.register.FinanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private final FinanceRepository financeRepository;
    private final LocalTaxRepository localTaxRepository;
    private final FinanceMapper financeMapper;

    public FinanceService(FinanceRepository financeRepository, FinanceMapper financeMapper,
                           LocalTaxRepository localTaxRepository) {
        this.financeRepository = financeRepository;
        this.localTaxRepository = localTaxRepository;
        this.financeMapper = financeMapper;
    }

    @Transactional
    public FinanceDto createFinance(FinanceDto financeDto) {
        Finance finance = financeMapper.toEntity(financeDto);
        assignLocalTaxAndTaxOffice(finance, financeDto);
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

    @Transactional(readOnly = true)
    public List<FinanceDto> getAllFinances() {
        return financeRepository.findAll().stream()
                .map(financeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FinanceDto getFinance(Long id) {
        return financeRepository.findById(id)
                .map(financeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
    }

    @Transactional
    public FinanceDto updateFinance(Long id, FinanceDto financeDto) {
        Finance finance = financeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Finance 정보를 찾을 수 없음"));
        financeMapper.updateDto(financeDto, finance);
        assignLocalTaxAndTaxOffice(finance, financeDto);
        finance = financeRepository.save(finance);
        return financeMapper.toDto(finance);
    }

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
