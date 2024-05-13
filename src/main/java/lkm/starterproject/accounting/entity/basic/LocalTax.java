package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Entity @Immutable
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "local_tax")
public class LocalTax {

    @Id
    @Column(name = "local_tax_id")
    private Long id;

    private String name;

    private String status;

}
