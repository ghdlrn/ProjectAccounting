package lkm.starterproject.auth.dto;

import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCompanyDto {

    private Long code;

    private Member member;

    private Company company;
}
