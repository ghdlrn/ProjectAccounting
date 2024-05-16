package lkm.starterproject.accounting.controller.register;

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

    @PostMapping
    public ResponseEntity<FinanceDto> createFinance(@RequestBody FinanceDto financeDto) {
        FinanceDto createdFinance = financeService.createFinance(financeDto);
        return ResponseEntity.ok(createdFinance);
    }

    @GetMapping
    public ResponseEntity<List<FinanceDto>> getAllFinances() {
        List<FinanceDto> finances = financeService.getAllFinances();
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceDto> getFinance(@PathVariable("id") Long id) {
        FinanceDto financeDto = financeService.getFinance(id);
        return ResponseEntity.ok(financeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceDto> updateFinance(@PathVariable("id") Long id, @RequestBody FinanceDto financeDto) {
        FinanceDto updatedFinance = financeService.updateFinance(id, financeDto);
        return ResponseEntity.ok(updatedFinance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable("id") Long id) {
        financeService.deleteFinance(id);
        return ResponseEntity.ok().build();
    }
}
