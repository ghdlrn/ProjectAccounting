package lkm.starterproject.accounting.controller.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.service.register.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        CardDto createdCard = cardService.createCard(cardDto);
        return ResponseEntity.ok(createdCard);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable("id") Long id) {
        CardDto cardDto = cardService.getCard(id);
        return ResponseEntity.ok(cardDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable("id") Long id, @RequestBody CardDto cardDto) {
        CardDto updatedCard = cardService.updateCard(id, cardDto);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok().build();
    }
}
