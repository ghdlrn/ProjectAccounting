package lkm.starterproject.auth.dto;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCompanyDto {

    private Long id;

    private MemberDto member;

    private CompanyDto company;
}
