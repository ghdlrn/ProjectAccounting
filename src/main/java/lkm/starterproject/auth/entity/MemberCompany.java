package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "member_company")
public class MemberCompany {

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

    private String role;    //회원의 회사에서의 권한
}
