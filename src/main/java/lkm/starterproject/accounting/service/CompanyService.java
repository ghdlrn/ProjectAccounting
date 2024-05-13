package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.CompanyMapper;
import lkm.starterproject.accounting.repository.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, TaxOfficeRepository taxOfficeRepository,
                          LocalTaxRepository localTaxRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.taxOfficeRepository = taxOfficeRepository;
        this.localTaxRepository = localTaxRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        LocalTax localTax = null;
        if (companyDto.getLocalTax() != null && companyDto.getLocalTax().getId() != null) {
            localTax = localTaxRepository.findById(companyDto.getLocalTax().getId())
                    .orElse(null);
        }

        TaxOffice taxOffice = null;
        if (companyDto.getTaxOffice() != null && companyDto.getTaxOffice().getId() != null) {
            taxOffice = taxOfficeRepository.findById(companyDto.getTaxOffice().getId())
                    .orElse(null);
        }

        Company company = companyMapper.toEntity(companyDto);
        company.setLocalTax(localTax);
        company.setTaxOffice(taxOffice);

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
    public CompanyDto getCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(companyMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Company 정보를 찾을 수 없음"));
    }

    @Transactional
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company 정보를 찾을 수 없음"));
        LocalTax localTax = null;
        if (companyDto.getLocalTax() != null && companyDto.getLocalTax().getId() != null) {
            localTax = localTaxRepository.findById(companyDto.getLocalTax().getId())
                    .orElse(null);
        }

        TaxOffice taxOffice = null;
        if (companyDto.getTaxOffice() != null && companyDto.getTaxOffice().getId() != null) {
            taxOffice = taxOfficeRepository.findById(companyDto.getTaxOffice().getId())
                    .orElse(null);
        }

        company.setLocalTax(localTax);
        company.setTaxOffice(taxOffice);
        companyMapper.updateEntityFromDto(companyDto, company);

        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company 정보를 찾을 수 없음"));
        companyRepository.delete(company);
    }
}
