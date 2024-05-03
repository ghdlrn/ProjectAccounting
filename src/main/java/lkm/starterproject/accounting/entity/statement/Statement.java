package lkm.starterproject.accounting.entity.statement;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.StatementTypeStatus;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Compendium;
import lkm.starterproject.accounting.entity.register.Customer;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statement")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "statement_code")
    private Long code;

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;     //등록일자

    @Enumerated(EnumType.STRING)
    private StatementTypeStatus statementTypeStatus;    //전표종류

    private Long debit;      //차변금액

    private Long credit;     //대변금액

}
