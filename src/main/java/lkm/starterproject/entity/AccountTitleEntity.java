package lkm.starterproject.entity;

import jakarta.persistence.*;
import lkm.starterproject.constants.DebitAndCreditStatus;
import lkm.starterproject.constants.UseStatus;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account_title")
public class AccountTitleEntity {        //계정과목

    @Id
    private int code;     //코드번호

    private String accountTitle;    //계정과목

    private DebitAndCreditStatus debitAndCredit;  //대차구분

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용구분

}
