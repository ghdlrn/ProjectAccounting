package lkm.starterproject.accounting.service.impl;

import jakarta.annotation.PostConstruct;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import lkm.starterproject.accounting.repository.register.AccountTitleRepository;
import lkm.starterproject.accounting.service.CSVService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {

    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;
    private final AccountTitleRepository accountTitleRepository;

    @Autowired
    public CSVServiceImpl(TaxOfficeRepository taxOfficeRepository , LocalTaxRepository localTaxRepository
    , AccountTitleRepository accountTitleRepository) {
        this.taxOfficeRepository = taxOfficeRepository;
        this.localTaxRepository = localTaxRepository;
        this.accountTitleRepository = accountTitleRepository;
    }

    @PostConstruct
    public void init() {
        saveTaxOfficeData();
        saveLocalTaxData();
    }

    public void saveLocalTaxData() {
        try (Reader reader = new InputStreamReader(new ClassPathResource("basic_data/local_taxes_data_20240501.csv").getInputStream(), "CP949")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<LocalTax> localTaxes = new ArrayList<>();
            for (CSVRecord record : records) {
                try {
                    Long id = Long.parseLong(record.get("법정동코드"));
                    String name = record.get("법정동명");
                    String status = record.get("폐지여부");

                    if("존재".equals(status)) {
                        LocalTax localTax = LocalTax.builder()
                                .id(id)
                                .name(name)
                                .status(status)
                                .build();
                        localTaxes.add(localTax);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing record: " + record.toString());
                    e.printStackTrace();
                }
            }
            Collections.sort(localTaxes, Comparator.comparing(LocalTax::getId, Comparator.nullsLast(Long::compareTo)));
            localTaxRepository.saveAll(localTaxes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTaxOfficeData() {         //세무서별 관할구역 csv파일 저장로직
        try (Reader reader = new InputStreamReader(new ClassPathResource("basic_data/tax_office_data_20220418.csv").getInputStream(), "CP949")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<TaxOffice> taxOffices = new ArrayList<>();
            for (CSVRecord record : records) {
                try {
                    Long id = parseLong(record.get("세무서코드"));
                    Long postCode = parseLong(record.get("우편번호"));
                    TaxOffice taxOffice = TaxOffice.builder()
                            .id(id)
                            .name(record.get("세무서명"))
                            .address(record.get("도로명 주소"))
                            .postCode(postCode)
                            .phone(record.get("전화번호"))
                            .fax(record.get("팩스번호"))
                            .accountNumber(record.get("계좌번호"))
                            .jurisdiction(record.get("관할구역"))
                            .build();
                    taxOffices.add(taxOffice);
                } catch (Exception e) {
                    System.err.println("Error processing record: " + record.toString());
                    e.printStackTrace();
                }
            }
            Collections.sort(taxOffices, Comparator.comparing(TaxOffice::getId));
            taxOfficeRepository.saveAll(taxOffices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAccountTitleData(Company company) {
        try (Reader reader = new InputStreamReader(new ClassPathResource("basic_data/account_title_data.csv").getInputStream(), "CP949")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<AccountTitle> accountTitles = new ArrayList<>();
            for (CSVRecord record : records) {
                try {
                    Long code = parseLong(record.get("계정코드"));
                    AccountTitle accountTitle = AccountTitle.builder()
                            .company(company)
                            .code(code)
                            .name(record.get("계정명"))
                            .balanceClassification(record.get("대차구분"))
                            .type(record.get("계정종류"))
                            .useStatus(UseStatus.valueOf(record.get("사용여부")))
                            .properties(record.get("계정속성"))
                            .relatedWork(record.get("관련업무"))
                            .cheque(record.get("수표"))
                            .balanceTallyClassification(record.get("잔액집계구분"))
                            .incomeExpenditureClassification(record.get("수입지출구분"))
                            .incomeExpenditureParentAccount(record.get("수입지출상위계정"))
                            .financialStatementName(record.get("재무제표표시명"))
                            .financialStatementPrintingLocation(record.get("재무제표인쇄위치"))
                            .financialStatementAmountThickness(record.get("재무제표금액굵기"))
                            .financialStatementAmountParentheses(record.get("재무재표금액괄호"))
                            .financialStatementHyperlinkTarget(record.get("재무제표하이퍼링크대상"))
                            .financialStatementParentAccount(record.get("재무제표상위계정"))
                            .build();
                    accountTitles.add(accountTitle);
                } catch (Exception e) {
                    System.err.println("Error processing record: " + record.toString());
                    e.printStackTrace();
                }
            }
            Collections.sort(accountTitles, Comparator.comparing(AccountTitle::getId));
            accountTitleRepository.saveAll(accountTitles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long parseLong(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Return null or a default value if the string is empty
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null; // Return null or log this as needed
        }
    }
}
