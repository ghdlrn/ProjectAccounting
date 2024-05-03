package lkm.starterproject.accounting.entity.basic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_code")
    private Long code;

    private Integer postcode;   //우편번호

    @Column(length = 50)
    private String roadAddress; //도로명주소

    @Column(length = 50)
    private String jibunAddress;    //지번주소

    @Column(length = 50)
    private String extraAddress;    //상세주소

    @Column(length = 50)
    private String guideText;   //주소 참고 항목
}
