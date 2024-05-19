package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;

import java.util.List;

public interface FinanceService {

    FinanceDto createFinance(FinanceDto financeDto);

    List<FinanceDto> getAllFinances();

    FinanceDto getFinance(Long id);

    FinanceDto updateFinance(Long id, FinanceDto financeDto);

    void deleteFinance(Long id);
}
