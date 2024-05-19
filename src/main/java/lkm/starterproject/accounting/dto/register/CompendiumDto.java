package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompendiumDto {

    private Long id;        //회사코드

    @NotBlank(message = "적요 내용은 필수 입력사항 입니다")
    private String content; //적요내용

    private String note;    //비고
}
