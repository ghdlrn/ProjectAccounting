package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompendiumDto {

    private Long id;

    private Long code;   //현금적요 코드

    @NotNull(message = "내용을 입력 해 주세요")
    private String content; //현금적요내용

    private String note;    //현금적요비고

}
