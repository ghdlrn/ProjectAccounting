package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = toEntity(companyDto);
        company = companyRepository.save(company);
        return toDto(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompanyDto getCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return toDto(company.get());
        } else {
            throw new RuntimeException("Company not found");
        }
    }

    @Transactional
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        updateEntityFromDto(companyDto, company);
        company = companyRepository.save(company);
        return toDto(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyDto toDto(Company company) {
        return new CompanyDto();
    }

    private Company toEntity(CompanyDto dto) {
        return new Company();
    }

    private void updateEntityFromDto(CompanyDto dto, Company company) {
    }
}
