package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.service.register.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @PostMapping
    public ResponseEntity<FinanceDto> createFinance(@Valid @RequestBody FinanceDto financeDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        FinanceDto createdFinance = financeService.createFinance(email, financeDto);
        return ResponseEntity.ok(createdFinance);
    }

    @GetMapping
    public ResponseEntity<List<FinanceDto>> getAllFinances() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<FinanceDto> finances = financeService.getAllFinances(email);
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceDto> getFinance(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        FinanceDto financeDto = financeService.getFinance(email, id);
        return ResponseEntity.ok(financeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceDto> updateFinance(@PathVariable("id") Long id, @Valid @RequestBody FinanceDto financeDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        FinanceDto updatedFinance = financeService.updateFinance(email, id, financeDto);
        return ResponseEntity.ok(updatedFinance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        financeService.deleteFinance(email, id);
        return ResponseEntity.ok().build();
    }
}
