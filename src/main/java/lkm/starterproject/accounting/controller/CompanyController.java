package lkm.starterproject.accounting.controller;

import lkm.starterproject.accounting.entity.company.Company;
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

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.ok(createdCompany);
    }

    // Retrieve all companies
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    // Retrieve a single company by ID
    @GetMapping("/{code}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long code) {
        Company company = companyService.getCompanyById(code);
        return ResponseEntity.ok(company);
    }

    // Update a company
    @PutMapping("/{code}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long code, @RequestBody Company companyDetails) {
        Company updatedCompany = companyService.updateCompany(code, companyDetails);
        return ResponseEntity.ok(updatedCompany);
    }

    // Delete a company
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long code) {
        companyService.deleteCompany(code);
        return ResponseEntity.ok().build();
    }
}
