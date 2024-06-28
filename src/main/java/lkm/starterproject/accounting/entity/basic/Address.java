package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String postcode;   //우편번호

    private String roadAddress; //도로명주소

    private String jibunAddress;    //지번주소

    private String extraAddress;    //상세주소

    private String guideText;   //참고사항
}
