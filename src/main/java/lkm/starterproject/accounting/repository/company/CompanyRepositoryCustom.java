package lkm.starterproject.accounting.repository.company;

import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.entity.Member;

import java.util.List;

public interface CompanyRepositoryCustom {
    List<Company> findByMember(Member member);
}
