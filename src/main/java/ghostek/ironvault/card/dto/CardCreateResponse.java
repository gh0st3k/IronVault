package ghostek.ironvault.card.dto;

import ghostek.ironvault.card.CardStatus;
import ghostek.ironvault.card.CardType;

import java.time.ZonedDateTime;

public record CardCreateResponse(String cardNumber, ZonedDateTime expirationDate, CardStatus status, CardType type) {
}
