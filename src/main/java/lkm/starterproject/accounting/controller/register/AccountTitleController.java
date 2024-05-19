package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.service.register.AccountTitleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/account-title")
public class AccountTitleController {

    private final AccountTitleService accountTitleService;

    public AccountTitleController(AccountTitleService accountTitleService) {
        this.accountTitleService = accountTitleService;
    }

    @PostMapping("/company/{companyId}")
    public ResponseEntity<AccountTitleDto> createAccountTitle(@PathVariable Long companyId, @Valid @RequestBody AccountTitleDto accountTitleDto) {
        AccountTitleDto createdAccountTitle = accountTitleService.createAccountTitle(companyId, accountTitleDto);
        return ResponseEntity.ok(createdAccountTitle);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<AccountTitleDto>> getAllAccountTitles(@PathVariable Long companyId) {
        List<AccountTitleDto> accountTitles = accountTitleService.getAllAccountTitles(companyId);
        return ResponseEntity.ok(accountTitles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTitleDto> getAccountTitle(@PathVariable("id") Long id) {
        AccountTitleDto accountTitleDto = accountTitleService.getAccountTitle(id);
        return ResponseEntity.ok(accountTitleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTitleDto> updateAccountTitle(@PathVariable("id") Long id, @Valid @RequestBody AccountTitleDto accountTitleDto) {
        AccountTitleDto updatedAccountTitle = accountTitleService.updateAccountTitle(id, accountTitleDto);
        return ResponseEntity.ok(updatedAccountTitle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountTitle(@PathVariable("id") Long id) {
        accountTitleService.deleteAccountTitle(id);
        return ResponseEntity.ok().build();
    }
}
