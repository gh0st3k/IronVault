package ghostek.ironvault.card;

import ghostek.ironvault.card.dto.CardCreateRequest;
import ghostek.ironvault.card.dto.CardCreateResponse;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public CardCreateResponse registryCard(CardCreateRequest request){
        Card card = createCard(request);

        cardRepository.save(card);
        return toResponse(card);
    }

    private Card createCard(CardCreateRequest request){
        Card card = new Card();
        card.setCardNumber(request.cardNumber());
        card.setExpirationDate(request.expirationDate());
        card.setStatus(request.status());
        card.setType(request.type());

        return card;
    }

    private CardCreateResponse toResponse(Card card){
        return new CardCreateResponse(card.getCardNumber(), card.getExpirationDate(), card.getStatus(), card.getType());
    }
}