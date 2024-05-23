package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.service.register.CompendiumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/compendium")
public class CompendiumController {

    private final CompendiumService compendiumService;

    public CompendiumController(CompendiumService compendiumService) {
        this.compendiumService = compendiumService;
    }

    @PostMapping("/account-title/{accountTitleId}")
    public ResponseEntity<CompendiumDto> createCompendium(@PathVariable("accountTitleId") Long accountTitleId, @Valid @RequestBody CompendiumDto compendiumDto) {
        if ( compendiumDto.getContent() == null || compendiumDto.getContent().isEmpty() ) {
            return ResponseEntity.badRequest().body(null); // 또는 적절한 에러 메시지를 포함한 ResponseEntity 반환
        }
        CompendiumDto createdCompendium = compendiumService.createCompendium(accountTitleId, compendiumDto);
        return ResponseEntity.ok(createdCompendium);
    }

    @GetMapping("/account-title/{accountTitleId}")
    public ResponseEntity<List<CompendiumDto>> getAllCompendiums(@PathVariable("accountTitleId") Long accountTitleId) {
        List<CompendiumDto> compendiums = compendiumService.getAllCompendiums(accountTitleId);
        return ResponseEntity.ok(compendiums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompendiumDto> getCompendium(@PathVariable("id") Long id) {
        CompendiumDto compendiumDto = compendiumService.getCompendium(id);
        return ResponseEntity.ok(compendiumDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompendiumDto> updateCompendium(@PathVariable("id") Long id, @Valid @RequestBody CompendiumDto compendiumDto) {
        CompendiumDto updatedCompendium = compendiumService.updateCompendium(id, compendiumDto);
        return ResponseEntity.ok(updatedCompendium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompendium(@PathVariable("id") Long id) {
        compendiumService.deleteCompendium(id);
        return ResponseEntity.ok().build();
    }
}
