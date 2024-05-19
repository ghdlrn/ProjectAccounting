package lkm.starterproject.accounting.controller.register;

import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.service.impl.register.CardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/card")
public class CardController {

    private final CardServiceImpl cardServiceImpl;

    public CardController(CardServiceImpl cardServiceImpl) {
        this.cardServiceImpl = cardServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        CardDto createdCard = cardServiceImpl.createCard(cardDto);
        return ResponseEntity.ok(createdCard);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cards = cardServiceImpl.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable("id") Long id) {
        CardDto cardDto = cardServiceImpl.getCard(id);
        return ResponseEntity.ok(cardDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable("id") Long id, @RequestBody CardDto cardDto) {
        CardDto updatedCard = cardServiceImpl.updateCard(id, cardDto);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable("id") Long id) {
        cardServiceImpl.deleteCard(id);
        return ResponseEntity.ok().build();
    }
}
