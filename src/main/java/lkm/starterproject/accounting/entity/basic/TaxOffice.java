package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tax_office")
public class TaxOffice {

    @Id
    private int code;   //세무서 코드

    private String name;    //세무서명

    private String address; //세무서주소

    private String postCode;    //우편번호

    private String phone;   //전화번호

    private String fax;     //팩스번호

    private String accountNumber;   //계좌번호

    private String jurisdiction;    //관할구역
}
