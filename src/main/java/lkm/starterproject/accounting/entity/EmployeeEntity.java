package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    private String name;    //사원명

    private UseStatus useStatus;    //사용여부

    private String phone;   //사원전화번호

    private String email;   //사원 이메일

    @Column(length = 14)
    private String residentRegistrationNumber;      //사원 주민번호
}
