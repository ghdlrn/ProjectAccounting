package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalTax {

    @Id
    @Column(name = "localtax_code", unique = true, nullable = false)
    private Long code;

    private String name;

    private String status;

}
