package lkm.starterproject.accounting.dto.basic;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;

    private String postcode;   //우편번호

    private String roadAddress; //도로명주소

    private String jibunAddress;    //지번주소

    private String extraAddress;    //상세주소

    private String guideText;   //참고사항
}
