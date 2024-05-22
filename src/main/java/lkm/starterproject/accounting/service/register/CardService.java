package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CardDto;

import java.util.List;

public interface CardService {

    CardDto createCard(String email, CardDto cardDto);

    List<CardDto> getAllCards(String email);

    CardDto getCard(String email, Long id);

    CardDto updateCard(String email, Long id, CardDto cardDto);

    void deleteCard(String email, Long id);
}
