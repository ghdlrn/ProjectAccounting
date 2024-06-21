package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.register.CardMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.register.CardRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.register.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CompanyService companyService;

    @Override
    @Transactional
    public CardDto createCard(String email, CardDto cardDto) {
        Company company = companyService.getCurrentCompany(email);
        Card card = cardMapper.toEntity(cardDto);
        card.setCompany(company);
        card.setCode(generateCardCode(company.getId()));
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDto> getAllCards(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<Card> cards = cardRepository.findByCompanyId(company.getId());
        return cardMapper.toDtoList(cards);
    }

    @Override
    @Transactional(readOnly = true)
    public CardDto getCard(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Card card = cardRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional
    public CardDto updateCard(String email, Long id, CardDto cardDto) {
        Company company = companyService.getCurrentCompany(email);
        Card card = cardRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        cardMapper.updateDto(cardDto, card);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional
    public void deleteCard(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Card card = cardRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("ACard 정보를 찾을 수 없음"));
        cardRepository.delete(card);
    }

    private Long generateCardCode(Long companyId) {
        List<Long> existingCodes = cardRepository.findCodesByCompanyId(companyId);
        Long code = 70001L;
        for (Long existingCode : existingCodes) {
            if (!existingCode.equals(code)) {break;}
            code++;
        }
        return code;
    }
}
