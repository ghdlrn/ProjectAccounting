package lkm.starterproject.accounting.dto.register;

import lkm.starterproject.accounting.entity.register.AccountTitle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompendiumDto {

    private Long id;        //회사코드

    private AccountTitle accountTitle;

    private String content; //적요내용

    private String note;    //비고
}
