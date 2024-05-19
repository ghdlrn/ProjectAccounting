package lkm.starterproject.accounting.controller.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.service.impl.register.FinanceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/finance")
public class FinanceController {

    private final FinanceServiceImpl financeServiceImpl;

    public FinanceController(FinanceServiceImpl financeServiceImpl) {
        this.financeServiceImpl = financeServiceImpl;
    }

    @PostMapping
    public ResponseEntity<FinanceDto> createFinance(@RequestBody FinanceDto financeDto) {
        FinanceDto createdFinance = financeServiceImpl.createFinance(financeDto);
        return ResponseEntity.ok(createdFinance);
    }

    @GetMapping
    public ResponseEntity<List<FinanceDto>> getAllFinances() {
        List<FinanceDto> finances = financeServiceImpl.getAllFinances();
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceDto> getFinance(@PathVariable("id") Long id) {
        FinanceDto financeDto = financeServiceImpl.getFinance(id);
        return ResponseEntity.ok(financeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceDto> updateFinance(@PathVariable("id") Long id, @RequestBody FinanceDto financeDto) {
        FinanceDto updatedFinance = financeServiceImpl.updateFinance(id, financeDto);
        return ResponseEntity.ok(updatedFinance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable("id") Long id) {
        financeServiceImpl.deleteFinance(id);
        return ResponseEntity.ok().build();
    }
}
