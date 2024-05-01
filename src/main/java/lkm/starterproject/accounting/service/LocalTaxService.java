package lkm.starterproject.accounting.service;

import jakarta.annotation.PostConstruct;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LocalTaxService {

    private final LocalTaxRepository localTaxRepository;

    @Autowired
    public LocalTaxService(LocalTaxRepository localTaxRepository) {
        this.localTaxRepository = localTaxRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        importLocalTaxEntitiesFromFile();
    }

    public void importLocalTaxEntitiesFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("basic_data/local_taxes_data_20240501.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            reader.readLine(); // 첫 줄은 헤더이므로 건너뜁니다.
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3 && "존재".equals(parts[2].trim())) {
                    LocalTax entity = new LocalTax();
                    entity.setCode(Long.parseLong(parts[0].trim()));
                    entity.setName(parts[1]);
                    entity.setStatus(parts[2].trim());
                    localTaxRepository.save(entity);
                }
            }
        }
    }
}
