package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
