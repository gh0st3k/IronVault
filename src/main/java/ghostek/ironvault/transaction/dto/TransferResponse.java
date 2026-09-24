package ghostek.ironvault.transaction.dto;

import ghostek.ironvault.transaction.TransactionStatus;
import ghostek.ironvault.transaction.TransactionType;

import java.time.ZonedDateTime;

public record TransferResponse(Long id, int amount, ZonedDateTime date, TransactionType type, TransactionStatus status, String description) {
}
