package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.DebitAndCreditStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account_title")
public class AccountTitle {        //계정과목

    @Id
    @Column(name="account_title_code")
    private Integer code;     //코드번호

    private String accountTitle;    //계정과목

    private DebitAndCreditStatus debitAndCredit;  //대차구분

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용구분


}
