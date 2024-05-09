package lkm.starterproject.accounting.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxOfficeDto {

    private Long code;

    private String name;

    private String jurisdiction;
}
