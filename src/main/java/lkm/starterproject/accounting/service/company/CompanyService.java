package lkm.starterproject.accounting.service.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto);

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompany(Long id);

    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompany(Long id);
}
