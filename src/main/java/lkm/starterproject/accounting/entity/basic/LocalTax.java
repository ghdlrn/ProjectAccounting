package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_tax")
public class LocalTax {

    @Id
    @Column(name = "local_tax_code" , unique = true, nullable = false)
    private Long code;

    private String name;

    private String status;
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
    @OneToOne(mappedBy = "localTax")
    private Company company;
}
