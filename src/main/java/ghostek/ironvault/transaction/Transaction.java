package ghostek.ironvault.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //used to create a random value for the argument ID
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private ZonedDateTime date; //the date the transaction was made with format "yyyy-mm-ddThh:mm:ss+02:00"

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type; //deposits, withdrawals, transfers, card payments, automated payments...

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status; //COMPLETED, PENDING, FAILED

    @Column(length = 255)
    private String description; //can be an empty value


}
