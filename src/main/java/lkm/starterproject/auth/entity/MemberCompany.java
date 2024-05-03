package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "member_company")
public class MemberCompany {  //회원-회사등록 다대다매핑연관용 엔티티

    @Id
    @Column(name = "member_company_code")
    private Long code;

}
