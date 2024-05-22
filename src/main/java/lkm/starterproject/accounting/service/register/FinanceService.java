package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;

import java.util.List;

public interface FinanceService {

    FinanceDto createFinance(String email, FinanceDto financeDto);

    List<FinanceDto> getAllFinances(String email);

    FinanceDto getFinance(String email, Long id);

    FinanceDto updateFinance(String email, Long id, FinanceDto financeDto);

    void deleteFinance(String email, Long id);
}
