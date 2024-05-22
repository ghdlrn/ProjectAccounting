package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompendiumDto {

    private Long id;

    private Long cashCode;   //현금적요 코드

    @NotNull(message = "내용을 입력 해 주세요")
    private String cashContent; //현금적요내용

    private String cashNote;    //현금적요비고

    private Long replacementCode;   //대체적요 코드

    @NotNull(message = "내용을 입력 해 주세요")
    private String replacementContent; //대체적요내용

    private String replacementNote;    //대체적요비고

}
