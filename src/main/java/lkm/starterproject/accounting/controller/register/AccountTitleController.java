package lkm.starterproject.accounting.controller.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.service.impl.register.AccountTitleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/account-title")
public class AccountTitleController {

    private final AccountTitleServiceImpl accountTitleServiceImpl;

    public AccountTitleController(AccountTitleServiceImpl accountTitleServiceImpl) {
        this.accountTitleServiceImpl = accountTitleServiceImpl;
    }

    @PostMapping
    public ResponseEntity<AccountTitleDto> createAccountTitle(@RequestBody AccountTitleDto accountTitleDto) {
        AccountTitleDto createdAccountTitle = accountTitleServiceImpl.createAccountTitle(accountTitleDto);
        return ResponseEntity.ok(createdAccountTitle);
    }

    @GetMapping
    public ResponseEntity<List<AccountTitleDto>> getAllAccountTitles() {
        List<AccountTitleDto> accountTitles = accountTitleServiceImpl.getAllAccountTitles();
        return ResponseEntity.ok(accountTitles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTitleDto> getAccountTitle(@PathVariable("id") Long id) {
        AccountTitleDto accountTitleDto = accountTitleServiceImpl.getAccountTitle(id);
        return ResponseEntity.ok(accountTitleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTitleDto> updateAccountTitle(@PathVariable("id") Long id, @RequestBody AccountTitleDto accountTitleDto) {
        AccountTitleDto updatedAccountTitle = accountTitleServiceImpl.updateAccountTitle(id, accountTitleDto);
        return ResponseEntity.ok(updatedAccountTitle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountTitle(@PathVariable("id") Long id) {
        accountTitleServiceImpl.deleteAccountTitle(id);
        return ResponseEntity.ok().build();
    }
}
