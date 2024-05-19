package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.service.register.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/finance")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping("/company/{companyId}")
    public ResponseEntity<FinanceDto> createFinance(@PathVariable Long companyId, @Valid @RequestBody FinanceDto financeDto) {
        FinanceDto createdFinance = financeService.createFinance(companyId, financeDto);
        return ResponseEntity.ok(createdFinance);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<FinanceDto>> getAllFinances(@PathVariable Long companyId) {
        List<FinanceDto> finances = financeService.getAllFinances(companyId);
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceDto> getFinance(@PathVariable("id") Long id) {
        FinanceDto financeDto = financeService.getFinance(id);
        return ResponseEntity.ok(financeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceDto> updateFinance(@PathVariable("id") Long id, @Valid @RequestBody FinanceDto financeDto) {
        FinanceDto updatedFinance = financeService.updateFinance(id, financeDto);
        return ResponseEntity.ok(updatedFinance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable("id") Long id) {
        financeService.deleteFinance(id);
        return ResponseEntity.ok().build();
    }
}
