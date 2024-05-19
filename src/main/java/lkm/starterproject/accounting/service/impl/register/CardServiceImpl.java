package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.register.CardMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.register.CardRepository;
import lkm.starterproject.accounting.service.register.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CompanyRepository companyRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CompanyRepository companyRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.companyRepository = companyRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    @Transactional
    public CardDto createCard(Long companyId, CardDto cardDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        Card card = cardMapper.toEntity(cardDto);
        card.setCompany(company);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDto> getAllCards(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        List<Card> cards = cardRepository.findByCompanyId(companyId);
        return cardMapper.toDtoList(cards);
    }

    @Override
    @Transactional(readOnly = true)
    public CardDto getCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional
    public CardDto updateCard(Long id, CardDto cardDto) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        cardMapper.updateDto(cardDto, card);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Override
    @Transactional
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ACard 정보를 찾을 수 없음"));
        cardRepository.delete(card);
    }
}
