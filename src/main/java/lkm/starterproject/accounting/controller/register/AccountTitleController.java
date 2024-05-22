package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.service.register.AccountTitleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/account-title")
public class AccountTitleController {

    private final AccountTitleService accountTitleService;

    public AccountTitleController(AccountTitleService accountTitleService) {
        this.accountTitleService = accountTitleService;
    }

    @PostMapping
    public ResponseEntity<AccountTitleDto> createAccountTitle(@Valid @RequestBody AccountTitleDto accountTitleDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountTitleDto createdAccountTitle = accountTitleService.createAccountTitle(email, accountTitleDto);
        return ResponseEntity.ok(createdAccountTitle);
    }

    @GetMapping
    public ResponseEntity<List<AccountTitleDto>> getAllAccountTitles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<AccountTitleDto> accountTitles = accountTitleService.getAllAccountTitles(email);
        return ResponseEntity.ok(accountTitles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTitleDto> getAccountTitle(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountTitleDto accountTitleDto = accountTitleService.getAccountTitle(email, id);
        return ResponseEntity.ok(accountTitleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTitleDto> updateAccountTitle(@PathVariable("id") Long id, @Valid @RequestBody AccountTitleDto accountTitleDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountTitleDto updatedAccountTitle = accountTitleService.updateAccountTitle(email, id, accountTitleDto);
        return ResponseEntity.ok(updatedAccountTitle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountTitle(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        accountTitleService.deleteAccountTitle(email, id);
        return ResponseEntity.ok().build();
    }
}
