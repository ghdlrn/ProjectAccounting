package lkm.starterproject.accounting.entity.document;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "purchase_and_sales_document")
public class PurchaseAndSalesDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_and_sales_document_id")
    private Long id;

    private String type;    //유형

    private String name;    //품명

    private Long amount;    //수량

    private Long unitPrice; //단가

    private Long valueOfSupply; //공급가액

    private Long surtax;        //부가세

    private Long total;     //합계

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
