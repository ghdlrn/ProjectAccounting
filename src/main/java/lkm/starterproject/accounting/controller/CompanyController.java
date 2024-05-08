package lkm.starterproject.accounting.controller;

import lkm.starterproject.accounting.dto.CompanyDto;
import lkm.starterproject.accounting.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Create a new company
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.createCompany(companyDto));
    }

    // Get a single company by ID
    @GetMapping("/{code}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long code) {
        return ResponseEntity.ok(companyService.getCompanyById(code));
    }

    // Get all companies
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    // Update an existing company
    @PutMapping("/{code}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long code, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(code, companyDto));
    }

    // Delete a company
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long code) {
        companyService.deleteCompany(code);
        return ResponseEntity.ok().build();
    }
}
