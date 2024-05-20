package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "member_company")
public class MemberCompany extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_company_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    private Role role;    //회원의 회사에서의 권한

    private boolean currentCompany; //현재 사용중인 회사 여부
}
