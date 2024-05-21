package lkm.starterproject.auth.dto;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberCompanyDto {

    private Long id;

    private MemberDto member;

    private CompanyDto company;

    private Role role;

    private boolean currentCompany;
}
