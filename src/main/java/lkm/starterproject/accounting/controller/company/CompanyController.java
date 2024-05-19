package lkm.starterproject.accounting.controller.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.service.impl.company.CompanyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/company")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createdCompany = companyServiceImpl.createCompany(companyDto);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyServiceImpl.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        CompanyDto companyDto = companyServiceImpl.getCompany(id);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDto companyDto) {
        CompanyDto updatedCompany = companyServiceImpl.updateCompany(id, companyDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companyServiceImpl.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

}
