package lkm.starterproject.accounting.dto.document;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lkm.starterproject.accounting.entity.register.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAndSalesDocumentDto {

    private Long id;

    private Long code;  //개인 코드

    private String type;    //유형

    private String name;    //품명

    private Long amount;    //수량

    private Long unitPrice; //단가

    private Long valueOfSupply; //공급가액

    private Long surtax;        //부가세

    private Long total;     //합계

    private Customer customer;

    private Finance finance;  //계좌

    private Card card;  //카드

    private AccountTitle accountTitle;  //계정과목

    private Compendium compendium;  //적요

    private Long debit; //차변

    private Long credit;    //대변
}
