package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="card_statement")
public class CardStatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //코드번호

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;     //등록일자

    @OneToOne
    @JoinColumn( name = "card_code")
    private CardEntity cardEntity;    //카드

    @OneToOne
    @JoinColumn( name = "account_title_code")
    private AccountTitleEntity accountTitleEntity;  //계정과목

    private String customerName;    //거래처명

    private Long money; //금액

    @OneToOne
    @JoinColumn( name = "compendium_code")
    private CompendiumEntity compendiumEntity;  //적요
}
