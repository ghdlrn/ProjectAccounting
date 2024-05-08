package lkm.starterproject.accounting.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyDto getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::companyToCompanyDto)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.companyDtoToCompany(companyDto);
        company = companyRepository.save(company);
        return companyMapper.companyToCompanyDto(company);
    }
}
