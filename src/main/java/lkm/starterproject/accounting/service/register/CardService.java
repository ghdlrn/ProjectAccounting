package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CardDto;

import java.util.List;

public interface CardService {

    CardDto createCard(CardDto cardDto);

    List<CardDto> getAllCards();

    CardDto getCard(Long id);

    CardDto updateCard(Long id, CardDto cardDto);

    void deleteCard(Long id);
}
