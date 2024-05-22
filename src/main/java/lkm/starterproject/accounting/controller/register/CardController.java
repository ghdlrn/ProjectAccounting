package lkm.starterproject.accounting.controller.register;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.dto.register.CardDto;
import lkm.starterproject.accounting.service.register.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardDto cardDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CardDto createdCard = cardService.createCard(email, cardDto);
        return ResponseEntity.ok(createdCard);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<CardDto> cards = cardService.getAllCards(email);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CardDto cardDto = cardService.getCard(email, id);
        return ResponseEntity.ok(cardDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable("id") Long id, @Valid @RequestBody CardDto cardDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CardDto updatedCard = cardService.updateCard(email, id, cardDto);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        cardService.deleteCard(email, id);
        return ResponseEntity.ok().build();
    }
}
