package lkm.starterproject.accounting.controller;

import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.service.CompanyService;
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
    public Company createCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @GetMapping("/{code}")
    public Company getCompany(@PathVariable Long code) {
        return companyService.getCompany(code);
    }

    @PutMapping("/{code}")
    public Company updateCompany(@PathVariable Long code, @RequestBody Company company) {
        company.setCode(code);
        return companyService.saveCompany(company);
    }

    @DeleteMapping("/{code}")
    public void deleteCompany(@PathVariable Long code) {
        companyService.deleteCompany(code);
    }

    @GetMapping
    public List<Company> listCompanies() {
        return companyService.listAllCompanies();
    }
}
