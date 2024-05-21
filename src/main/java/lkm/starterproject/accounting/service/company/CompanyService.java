package lkm.starterproject.accounting.service.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;

import java.util.List;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto, String email);

    List<CompanyDto> getAllCompaniesByMember(String email);

    CompanyDto getCompany(Long id);

    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompany(Long id);

    void selectCompany(Long companyId, String email);

    void assignRole(Long companyId, String email, String role);

    Company getCurrentCompany(String email);    //사용자가 선택한 회사와 관련한 엔티티 CRUD작업할때 선택한 회사 등록
}
