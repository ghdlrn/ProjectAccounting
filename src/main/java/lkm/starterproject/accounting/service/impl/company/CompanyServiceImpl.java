package lkm.starterproject.accounting.service.impl.company;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.Member;

import lkm.starterproject.auth.entity.MemberCompany;
import lkm.starterproject.auth.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MemberRepository memberRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, MemberRepository memberRepository,
                              TaxOfficeRepository taxOfficeRepository, LocalTaxRepository localTaxRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.memberRepository = memberRepository;
        this.taxOfficeRepository = taxOfficeRepository;
        this.localTaxRepository = localTaxRepository;
    }

    @Override
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto, String email) {
        Company company = companyMapper.toEntity(companyDto);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        MemberCompany memberCompany = new MemberCompany();
        memberCompany.setMember(member);
        memberCompany.setCompany(company);
        memberCompany.setRole(Role.MASTER.name());
        company.getMemberCompanies().add(memberCompany);
        assignLocalTaxAndTaxOffice(company, companyDto);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toDtoList(companies);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto getCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        assignLocalTaxAndTaxOffice(company, companyDto);
        companyMapper.updateDto(companyDto, company);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public void assignRole(Long companyId, String email, String role) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        for (MemberCompany memberCompany : company.getMemberCompanies()) {
            if (memberCompany.getMember().getEmail().equals(email)) {
                memberCompany.setRole(role);
                return;
            }
        }
        MemberCompany newMemberCompany = new MemberCompany();
        newMemberCompany.setMember(member);
        newMemberCompany.setCompany(company);
        newMemberCompany.setRole(role);
        company.getMemberCompanies().add(newMemberCompany);
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
