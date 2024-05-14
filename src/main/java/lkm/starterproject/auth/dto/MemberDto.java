package lkm.starterproject.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDto {

    private String username;

    private String email;

    private String password;

    private List<MemberCompanyDto> memberCompanies;
}
