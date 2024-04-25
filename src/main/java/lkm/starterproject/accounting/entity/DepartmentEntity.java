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
@Table(name="department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    
    private String name;    //부서명
    
    private UseStatus useStatus;    //사용여부
}
