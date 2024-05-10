package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.CompanyMapper;
import lkm.starterproject.accounting.repository.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final LocalTaxRepository localTaxRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, LocalTaxRepository localTaxRepository, TaxOfficeRepository taxOfficeRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.localTaxRepository = localTaxRepository;
        this.taxOfficeRepository = taxOfficeRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompanyDto getCompany(Long code) {
        Optional<Company> company = companyRepository.findById(code);
        return company.map(companyMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Transactional
    public CompanyDto updateCompany(Long code, CompanyDto companyDto) {
        Company company = companyRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyMapper.updateEntityFromDto(companyDto, company);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Transactional
    public void deleteCompany(Long code) {
        Company company = companyRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }
}
