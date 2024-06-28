package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Immutable @Table(name = "local_tax")
public class LocalTax {

    @Id
    @Column(name = "local_tax_id")
    private Long id;

    private String name;

    private String status;

}
