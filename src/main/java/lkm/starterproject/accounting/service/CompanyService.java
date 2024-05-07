package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long code) {
        Optional<Company> company = companyRepository.findById(code);
        return company.orElseThrow(() -> new RuntimeException("Company not found for this code :: " + code));
    }

    public Company updateCompany(Long code, Company companyDetails) {
        Company company = getCompanyById(code);
        company.setName(companyDetails.getName());
        company.setBusinessRegistrationNumber(companyDetails.getBusinessRegistrationNumber());
        return companyRepository.save(company);
    }

    public void deleteCompany(Long code) {
        companyRepository.deleteById(code);
    }
}
