package lkm.starterproject.accounting.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaxOfficeDto {

    private Integer code;

    private String name;

    private String jurisdiction;
}
