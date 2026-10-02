package ghostek.ironvault.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cardNumber;

    @Column(nullable = false)
    private ZonedDateTime expirationDate;

    @Column(nullable = false)
    private CardStatus status;

    @Column(nullable = false)
    private CardType type;
}
