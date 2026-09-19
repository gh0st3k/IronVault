package ghostek.ironvault.transaction;

import java.time.*;

public class Transaction {
    private int id;
    private int amount;
    private ZonedDateTime date; //the date the transaction was made with format "yyyy-mm-ddThh:mm:ss+02:00"
    private TransactionType type; //deposits, withdrawals, transfers, card payments, automated payments...
    private int status; //0 (FINISHED CORRECTLY) or 1 (NOT FINISHED)
    private String description; //can be an empty value
}
