package lkm.starterproject.accounting.controller.company;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.service.company.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CompanyDto createdCompany = companyService.createCompany(companyDto, email);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<CompanyDto> companies = companyService.getAllCompaniesByMember(email);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        CompanyDto companyDto = companyService.getCompany(id);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDto companyDto) {
        CompanyDto updatedCompany = companyService.updateCompany(id, companyDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/select")
    public ResponseEntity<Void> selectCompany(@RequestBody Long companyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        companyService.selectCompany(companyId, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/assign-role")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<Void> assignRole(@PathVariable("id") Long companyId, @RequestParam("email") String email, @RequestParam("role") String role) {
        companyService.assignRole(companyId, email, role);
        return ResponseEntity.ok().build();
    }
}
