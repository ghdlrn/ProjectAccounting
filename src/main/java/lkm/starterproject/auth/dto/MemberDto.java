package lkm.starterproject.auth.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String username;

    private String email;

    private String password;
}
