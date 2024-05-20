package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {     //회원

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable=false, length=16)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;      //권한
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberCompany> memberCompanies = new ArrayList<>();


}
