package lkm.starterproject.accounting.entity.statement;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Compendium;
import lkm.starterproject.accounting.entity.register.Finance;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cash_statement")
public class CashStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //코드번호

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;     //등록일자


    
    private String customerName;    //거래처명
    
    private Long money; //금액
    
    private Long commission;    //수수료
    

}
