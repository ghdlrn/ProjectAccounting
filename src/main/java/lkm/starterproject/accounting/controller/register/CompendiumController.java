package lkm.starterproject.accounting.controller.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.service.impl.register.CompendiumServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/account-title/compendium")
public class CompendiumController {

    private final CompendiumServiceImpl compendiumServiceImpl;

    public CompendiumController(CompendiumServiceImpl compendiumServiceImpl) {
        this.compendiumServiceImpl = compendiumServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CompendiumDto> createCompendium(@RequestBody CompendiumDto compendiumDto) {
        CompendiumDto createdCompendium = compendiumServiceImpl.createCompendium(compendiumDto);
        return ResponseEntity.ok(createdCompendium);
    }

    @GetMapping
    public ResponseEntity<List<CompendiumDto>> getAllCompendiums() {
        List<CompendiumDto> compendiums = compendiumServiceImpl.getAllCompendiums();
        return ResponseEntity.ok(compendiums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompendiumDto> getCompendium(@PathVariable("id") Long id) {
        CompendiumDto compendiumDto = compendiumServiceImpl.getCompendium(id);
        return ResponseEntity.ok(compendiumDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompendiumDto> updateCompendium(@PathVariable("id") Long id, @RequestBody CompendiumDto compendiumDto) {
        CompendiumDto updatedCompendium = compendiumServiceImpl.updateCompendium(id, compendiumDto);
        return ResponseEntity.ok(updatedCompendium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompendium(@PathVariable("id") Long id) {
        compendiumServiceImpl.deleteCompendium(id);
        return ResponseEntity.ok().build();
    }
}
