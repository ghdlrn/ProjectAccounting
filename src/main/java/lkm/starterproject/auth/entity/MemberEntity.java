package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="member")
public class MemberEntity {     //회원

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=16)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;      //권한

    private LocalDateTime createdAt;    //계정생성일

    private LocalDateTime updatedAt;    //계정업데이트일

    @OneToMany(mappedBy = "member")
    private List<MemberCompanyEntity> memberCompanyEntities = new ArrayList<>();
}
