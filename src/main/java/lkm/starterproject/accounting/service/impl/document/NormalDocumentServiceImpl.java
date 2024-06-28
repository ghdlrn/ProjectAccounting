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

@Service
@RequiredArgsConstructor
public class NormalDocumentServiceImpl implements NormalDocumentService {

    private final NormalDocumentRepository normalDocumentRepository;
    private final NormalDocumentMapper normalDocumentMapper;
    private final CompanyService companyService;

    @Override
    @Transactional
    public List<NormalDocumentDto> createNormalDocuments(String email, List<NormalDocumentDto> normalDocumentDtos) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentMapper.toEntityList(normalDocumentDtos);
        normalDocuments.forEach(normalDocument -> normalDocument.setCompany(company));
        normalDocuments = normalDocumentRepository.saveAll(normalDocuments);
        return normalDocumentMapper.toDtoList(normalDocuments);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NormalDocumentDto> getAllNormalDocuments(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentRepository.findByCompanyId(company.getId());
        return normalDocumentMapper.toDtoList(normalDocuments);
    }

    @Override
    @Transactional(readOnly = true)
    public NormalDocumentDto getNormalDocument(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        NormalDocument normalDocument = normalDocumentRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("NormalDocument 정보를 찾을 수 없음"));
        return normalDocumentMapper.toDto(normalDocument);
    }

    @Override
    @Transactional
    public NormalDocumentDto updateNormalDocument(String email, Long id, NormalDocumentDto normalDocumentDto) {
        Company company = companyService.getCurrentCompany(email);
        NormalDocument normalDocument = normalDocumentRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("NormalDocument 정보를 찾을 수 없음"));
        normalDocumentMapper.updateDto(normalDocumentDto, normalDocument);
        normalDocument = normalDocumentRepository.save(normalDocument);
        return normalDocumentMapper.toDto(normalDocument);
    }

    @Override
    @Transactional
    public void deleteNormalDocument(String email, LocalDate date) {
        Company company = companyService.getCurrentCompany(email);
        List<NormalDocument> normalDocuments = normalDocumentRepository.findByCompanyIdAndDate(company.getId(), date);
        if (normalDocuments.isEmpty()) {
            throw new EntityNotFoundException("해당 날짜에 대한 NormalDocument 정보를 찾을 수 없음");
        }
        normalDocumentRepository.deleteAll(normalDocuments);
    }
}
