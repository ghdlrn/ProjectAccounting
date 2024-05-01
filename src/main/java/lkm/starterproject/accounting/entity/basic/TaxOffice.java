package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.Column;
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
public class TaxOffice {

    @Id
    @Column(unique = true, nullable = false)
    private Integer code;   //세무서 코드

    private String name;    //세무서명

    private String address; //도로명주소

    private Integer postCode;    //우편번호

    private String phone;   //전화번호

    private String fax;     //팩스번호

    private String accountNumber;   //계좌번호

    private String jurisdiction;    //관할구역
}
