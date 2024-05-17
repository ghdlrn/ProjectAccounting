package lkm.starterproject.auth.dto;

import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Role role;

    private MemberCompanyDto memberCompanies;
}
