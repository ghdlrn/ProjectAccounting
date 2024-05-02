package lkm.starterproject.accounting.controller;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
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
public class CompanyRegisterController {

    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;

    @GetMapping("/register/company-info/tax-office")
    public List<TaxOfficeDto> getAllTaxOffices() {      //사업장 세무서 조회
        return taxOfficeRepository.findAll().stream()
                .map(taxOffice -> new TaxOfficeDto(
                        taxOffice.getCode(),
                        taxOffice.getName(),
                        taxOffice.getJurisdiction()))
                .collect(Collectors.toList());
    }

    @GetMapping("/register/company-info/local-tax")
    public List<LocalTaxDto> getAllLocalTax() {      //사업장 세무서 조회
        return localTaxRepository.findAll().stream()
                .map(localTax -> new LocalTaxDto(
                        localTax.getCode(),
                        localTax.getName(),
                        localTax.getStatus()))
                .collect(Collectors.toList());
    }
}
