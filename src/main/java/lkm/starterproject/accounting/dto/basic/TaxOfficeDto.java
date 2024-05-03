package lkm.starterproject.accounting.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaxOfficeDto {

    private Long code;

    private String name;

    private String jurisdiction;
}
