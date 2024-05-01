package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long code;

    private String name;

    private String status;
}
