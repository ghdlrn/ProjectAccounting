package lkm.starterproject.accounting.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class LocalTaxDto {

    private Long code;

    private String name;

    private String status;
}
