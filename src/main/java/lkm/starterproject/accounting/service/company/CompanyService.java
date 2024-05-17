package lkm.starterproject.accounting.service.company;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper,
                          TaxOfficeRepository taxOfficeRepository, LocalTaxRepository localTaxRepository) {
        this.companyRepository = companyRepository;
        this.taxOfficeRepository = taxOfficeRepository;
        this.localTaxRepository = localTaxRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        assignLocalTaxAndTaxOffice(company, companyDto);
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
        return companyRepository.findById(id)
                .map(companyMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
    }

    @Transactional
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        companyMapper.updateDto(companyDto, company);
        assignLocalTaxAndTaxOffice(company, companyDto);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        companyRepository.delete(company);
    }

    private void assignLocalTaxAndTaxOffice(Company company, CompanyDto companyDto) {
        if (companyDto.getLocalTax() != null && companyDto.getLocalTax().getId() != null) {
            company.setLocalTax(findLocalTax(companyDto.getLocalTax().getId()));
        } else {
            company.setLocalTax(null);
        }

        if (companyDto.getTaxOffice() != null && companyDto.getTaxOffice().getId() != null) {
            company.setTaxOffice(findTaxOffice(companyDto.getTaxOffice().getId()));
        } else {
            company.setTaxOffice(null);
        }
    }

    private LocalTax findLocalTax(Long id) {
        return id == null ? null : localTaxRepository.findById(id).orElse(null);
    }

    private TaxOffice findTaxOffice(Long id) {
        return id == null ? null : taxOfficeRepository.findById(id).orElse(null);
    }
}
