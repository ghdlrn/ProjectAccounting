package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.dto.register.CardDto;

import java.util.List;

public interface CardService {

    CardDto createCard(Long companyId, CardDto cardDto);

    List<CardDto> getAllCards(Long companyId);

    CardDto getCard(Long id);

    CardDto updateCard(Long id, CardDto cardDto);

    void deleteCard(Long id);
}
