package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.mapper.register.CardMapper;
import lkm.starterproject.accounting.repository.register.CardRepository;
import lkm.starterproject.accounting.service.register.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Transactional
    public CardDto createCard(CardDto cardDto) {
        Card card = cardMapper.toEntity(cardDto);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Transactional(readOnly = true)
    public List<CardDto> getAllCards() {
        return cardRepository.findAll().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CardDto getCard(Long id) {
        return cardRepository.findById(id)
                .map(cardMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
    }

    @Transactional
    public CardDto updateCard(Long id, CardDto cardDto) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        cardMapper.updateDto(cardDto, card);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Transactional
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card 정보를 찾을 수 없음"));
        cardRepository.delete(card);
    }
}
