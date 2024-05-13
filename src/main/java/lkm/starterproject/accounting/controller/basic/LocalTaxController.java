package lkm.starterproject.accounting.controller.basic;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LocalTaxController {

    private final LocalTaxRepository localTaxRepository;

    @GetMapping("/register/company/local-tax")
    public List<LocalTaxDto> getAllLocalTax() {      //지방세 법정동 조회
        return localTaxRepository.findAll().stream()
                .map(localTax -> new LocalTaxDto(
                        localTax.getId(),
                        localTax.getName()))
                .collect(Collectors.toList());
    }
}
