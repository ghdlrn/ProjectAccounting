package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Create a new company
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = convertToEntity(companyDto);
        company = companyRepository.save(company);
        return convertToDto(company);
    }

    // Get a single company by ID
    public CompanyDto getCompanyById(Long code) {
        Company company = companyRepository.findById(code).orElseThrow(() -> new RuntimeException("Company not found"));
        return convertToDto(company);
    }

    // Get all companies
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Update an existing company
    public CompanyDto updateCompany(Long code, CompanyDto companyDto) {
        Company company = companyRepository.findById(code).orElseThrow(() -> new RuntimeException("Company not found"));
        // Update entity fields here
        company = companyRepository.save(company);
        return convertToDto(company);
    }

    // Delete a company
    public void deleteCompany(Long code) {
        Company company = companyRepository.findById(code).orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }

    // Helper to convert DTO to Entity
    private Company convertToEntity(CompanyDto companyDto) {
        // Implement this method based on your DTO and Entity structure
        return new Company(); // Placeholder
    }

    // Helper to convert Entity to DTO
    private CompanyDto convertToDto(Company company) {
        // Implement this method based on your DTO and Entity structure
        return new CompanyDto(); // Placeholder
    }
}
