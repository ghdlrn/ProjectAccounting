package lkm.starterproject.accounting.controller.basic;

import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TaxOfficeController {

    private final TaxOfficeRepository taxOfficeRepository;

    @GetMapping("/register/company/tax-office")
    public List<TaxOfficeDto> getAllTaxOffices() {      //사업장 세무서 조회
        return taxOfficeRepository.findAll().stream()
                .map(taxOffice -> new TaxOfficeDto(
                        taxOffice.getCode(),
                        taxOffice.getName(),
                        taxOffice.getJurisdiction()))
                .collect(Collectors.toList());
    }

}
