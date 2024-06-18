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
import lkm.starterproject.accounting.service.CSVService;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.Member;

import lkm.starterproject.auth.entity.MemberCompany;
import lkm.starterproject.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MemberRepository memberRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;
    private final CSVService csvService;

    @Override
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto, String email) {
        Company company = companyMapper.toEntity(companyDto);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        MemberCompany memberCompany = MemberCompany.builder()
                .member(member)
                .company(company)
                .role(Role.MASTER)
                .currentCompany(false)
                .code(generateMemberCompanyCode(member))
                .build();
        company.getMemberCompanies().add(memberCompany);
        assignLocalTaxAndTaxOffice(company, companyDto);
        company = companyRepository.save(company);
        csvService.saveAccountTitleData(company);
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompaniesByMember(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        List<Company> companies = companyRepository.findByMember(member);
        return companies.stream()
                .map(company -> {
                    boolean currentCompany = company.getMemberCompanies().stream()
                            .anyMatch(mc -> mc.getMember().equals(member) && mc.isCurrentCompany());
                    CompanyDto companyDto = companyMapper.toDtoWithCurrentCompany(company, currentCompany);
                    companyDto.setMemberCompanyCode(getMemberCompanyCodeForMemberAndCompany(member, company));
                    return companyDto;
                })
                .collect(Collectors.toList());
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
        csvService.saveAccountTitleData(company);
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
    public void selectCompany(Long companyId, String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        // 모든 회사의 currentCompany를 false로 변경
        for (MemberCompany memberCompany : member.getMemberCompanies()) {
            memberCompany.setCurrentCompany(false);
        }
        // 선택한 회사의 currentCompany를 true로 변경
        MemberCompany selectedCompany = member.getMemberCompanies().stream()
                .filter(mc -> mc.getCompany().getId().equals(companyId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("MemberCompany not found"));
        selectedCompany.setCurrentCompany(true);

        memberRepository.save(member);
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
                memberCompany.setRole(Role.valueOf(role));
                return;
            }
        }
        MemberCompany newMemberCompany = new MemberCompany();
        newMemberCompany.setMember(member);
        newMemberCompany.setCompany(company);
        newMemberCompany.setRole(Role.valueOf(role));
        company.getMemberCompanies().add(newMemberCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public Company getCurrentCompany(String email) {        //사용자가 선택한 Company정보 가져옴
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }

        return member.getMemberCompanies().stream()
                .filter(MemberCompany::isCurrentCompany)
                .map(MemberCompany::getCompany)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Current company not found"));
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

    private Long generateMemberCompanyCode(Member member) {
        List<MemberCompany> memberCompanies = member.getMemberCompanies();
        if (memberCompanies.isEmpty()) {
            return 1L;
        }
        List<Long> existingCodes = memberCompanies.stream()
                .map(MemberCompany::getCode)
                .sorted()
                .toList();
        for (int i = 0; i < existingCodes.size(); i++) {
            if (existingCodes.get(i) != i + 1) {
                return (long) (i + 1);
            }
        }
        return (long) (existingCodes.size() + 1);
    }

    private Long getMemberCompanyCodeForMemberAndCompany(Member member, Company company) {
        return company.getMemberCompanies().stream()
                .filter(mc -> mc.getMember().equals(member))
                .map(MemberCompany::getCode)
                .findFirst()
                .orElse(null);
    }
}
