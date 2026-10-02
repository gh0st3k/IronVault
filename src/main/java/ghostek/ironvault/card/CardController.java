package ghostek.ironvault.card;

import ghostek.ironvault.card.dto.CardCreateRequest;
import ghostek.ironvault.card.dto.CardCreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @PostMapping
    public CardCreateResponse registryCard(@RequestBody CardCreateRequest request){
        return cardService.registryCard(request);
    }
}
