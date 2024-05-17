package lkm.starterproject.auth.dto;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberCompanyDto {

    private Long id;

    private MemberDto member;

    private CompanyDto company;

    private String role;
}
