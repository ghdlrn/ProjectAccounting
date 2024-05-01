package lkm.starterproject.accounting.service;

import jakarta.annotation.PostConstruct;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
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
public class CSVService {

    private final TaxOfficeRepository taxOfficeRepository;

    @Autowired
    public CSVService(TaxOfficeRepository taxOfficeRepository) {
        this.taxOfficeRepository = taxOfficeRepository;
    }

    @PostConstruct
    public void init() {
        loadCSVData();
    }

    public void loadCSVData() {         //세무서별 관할구역 csv파일 저장로직
        try (Reader reader = new InputStreamReader(new ClassPathResource("basic_data/tax_office_data_20220418.csv").getInputStream(), "CP949")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<TaxOffice> taxOffices = new ArrayList<>();
            for (CSVRecord record : records) {
                try {
                    Integer code = parseInteger(record.get("세무서코드"));
                    Integer postCode = parseInteger(record.get("우편번호"));
                    TaxOffice taxOffice = TaxOffice.builder()
                            .code(code)
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
            Collections.sort(taxOffices, Comparator.comparing(TaxOffice::getCode));
            taxOfficeRepository.saveAll(taxOffices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Return null or a default value if the string is empty
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null; // Return null or log this as needed
        }
    }
}
