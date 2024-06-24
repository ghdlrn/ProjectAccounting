package lkm.starterproject.accounting.service.impl.document;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.document.NormalDocumentDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.document.NormalDocument;
import lkm.starterproject.accounting.mapper.document.NormalDocumentMapper;
import lkm.starterproject.accounting.repository.document.NormalDocumentRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.document.NormalDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormalDocumentServiceImpl implements NormalDocumentService {

    private final NormalDocumentRepository normalDocumentRepository;
    private final NormalDocumentMapper normalDocumentMapper;
    private final CompanyService companyService;

    @Override
    @Transactional(readOnly = true)
    public List<NormalDocumentDto> getAllNormalDocuments(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentRepository.findByCompanyId(company.getId());
        return normalDocumentMapper.toDtoList(normalDocuments);
    }

    @Override
    @Transactional
    public List<NormalDocumentDto> createOrUpdateNormalDocuments(String email, List<NormalDocumentDto> normalDocumentDto) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentDto.stream()
                .map(normalDocumentMapper::toEntity)
                .peek(document -> document.setCompany(company))
                .collect(Collectors.toList());
        normalDocuments = normalDocumentRepository.saveAll(normalDocuments);
        return normalDocumentMapper.toDtoList(normalDocuments);
    }

    @Override
    @Transactional
    public void deleteNormalDocumentsByDate(String email, LocalDate date) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentRepository.findByCompanyIdAndDate(company.getId(), date);
        normalDocumentRepository.deleteAll(normalDocuments);
    }
}
