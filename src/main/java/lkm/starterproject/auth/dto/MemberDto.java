package lkm.starterproject.auth.dto;

import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    private String username;

    private String email;

    private String password;
}
