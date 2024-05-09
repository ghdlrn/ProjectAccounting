package lkm.starterproject.accounting.controller.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
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
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createdCompany = companyService.createCompany(companyDto);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long code) {
        CompanyDto companyDto = companyService.getCompany(code);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{code}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long code, @RequestBody CompanyDto companyDto) {
        CompanyDto updatedCompany = companyService.updateCompany(code, companyDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long code) {
        companyService.deleteCompany(code);
        return ResponseEntity.ok().build();
    }

}
