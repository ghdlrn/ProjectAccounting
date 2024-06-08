package lkm.starterproject.accounting.dto.document;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NormalDocumentDto {

    @NotBlank(message = "날짜는 필수 입력사항 입니다")
    private LocalDate date;     //일자

    @NotBlank(message = "구분은 필수 입력사항 입니다")
    private String division;    //구분

    @NotBlank(message = "계정과목은 필수 입력사항 입니다")
    private AccountTitle accountTitle;

    @NotBlank(message = "거래처는 필수 입력사항 입니다")
    private Customer customer;

    private Long debit;

    private Long credit;
}
