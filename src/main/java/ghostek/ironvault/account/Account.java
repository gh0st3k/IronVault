package ghostek.ironvault.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
public class Account {
    @Id
    private Long IBAN;

    @Column(nullable = false)
    private AccountCurrency currency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private AccountStatus status;

    @Column
    private ZonedDateTime createdAt;
}
