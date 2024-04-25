package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.StatementTypeStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="statement")
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;     //등록일자

    @Enumerated(EnumType.STRING)
    private StatementTypeStatus statementTypeStatus;    //전표종류

    @OneToOne
    @JoinColumn(name = "accounting_title_code")
    private AccountTitleEntity accountTitleEntity;  //계정과목

    @OneToOne
    @JoinColumn(name = "customer_code")
    private CustomerEntity customerEntity;      //거래처

    @OneToOne
    @JoinColumn(name = "compendium_code")
    private CompendiumEntity compendiumEntity;  //적요

    private Long debit;      //차변금액

    private Long credit;     //대변금액

}
