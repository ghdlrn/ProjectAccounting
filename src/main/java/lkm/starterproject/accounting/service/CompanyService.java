package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.repository.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TaxOfficeRepository taxOfficeRepository;

    public CompanyService(CompanyRepository companyRepository, TaxOfficeRepository taxOfficeRepository) {
        this.companyRepository = companyRepository;
        this.taxOfficeRepository = taxOfficeRepository;
    }

    public Company addCompany(CompanyDto companyDto) {
        TaxOffice taxOffice = taxOfficeRepository.findByName(companyDto.getTaxOfficeName())
                .orElseThrow(() -> new RuntimeException("Tax office not found"));

        Company company = new Company();
        // Set other company fields from companyDto
        company.setTaxOffice(taxOffice);
        return companyRepository.save(company);
    }
}
