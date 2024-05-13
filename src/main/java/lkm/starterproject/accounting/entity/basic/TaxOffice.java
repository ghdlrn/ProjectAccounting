package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Entity @Immutable
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "tax_office")
public class TaxOffice {

    @Id
    @Column(name = "tax_office_id")
    private Long id;   //세무서 코드

    private String name;    //세무서명

    private String address; //도로명주소

    private Long postCode;    //우편번호

    private String phone;   //전화번호

    private String fax;     //팩스번호

    private String accountNumber;   //계좌번호

    private String jurisdiction;    //관할구역

}
