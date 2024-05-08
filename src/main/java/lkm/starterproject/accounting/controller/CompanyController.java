package lkm.starterproject.accounting.controller;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long code) {
        return ResponseEntity.ok(companyService.getCompanyById(code));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto savedCompany = companyService.createCompany(companyDto);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

}
