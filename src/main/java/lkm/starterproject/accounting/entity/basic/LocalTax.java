package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Entity @Immutable
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "local_tax")
public class LocalTax {

    @Id
    @Column(name = "local_tax_id" , unique = true, nullable = false)
    private Long id;

    private String name;

    private String status;

}
