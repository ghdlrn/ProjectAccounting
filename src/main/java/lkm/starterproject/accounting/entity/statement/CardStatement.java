package lkm.starterproject.accounting.entity.statement;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.entity.register.Compendium;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_statement")
public class CardStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_statement_code")
    private Long code;        //코드번호

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;     //등록일자



    private String customerName;    //거래처명

    private Long money; //금액


}
