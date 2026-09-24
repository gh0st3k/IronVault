package ghostek.ironvault.transaction;

import ghostek.ironvault.transaction.dto.TransferRequest;
import ghostek.ironvault.transaction.dto.TransferResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class TransactionService {

    TransactionRepository transactionRepository; //we instantiate the repository to dump the new data into the DB

    @Transactional
    public TransferResponse transfer(TransferRequest request){ //we call the DTO (TransferRequest) so we can get the required data
        Transaction transaction = createTransaction(request.amount(), request.type(), request.status(), request.description()); //we create the Transaction

        transactionRepository.save(transaction); //we save the transfer into the DB
        return toResponse(transaction); //we return the Transaction object turned into a response DTO (TransferResponse)
    }

    public Transaction createTransaction(int amount, TransactionType transactionType, TransactionStatus transactionStatus, String description){
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(ZonedDateTime.now());
        transaction.setType(transactionType);
        transaction.setStatus(transactionStatus); //we have to keep in mind that right now, the status is PENDING, once the money's been transferred, the status will change to COMPLETED
        transaction.setDescription(description);

        return transaction;
    }

    public TransferResponse toResponse(Transaction transaction){ //function used to create a response DTO,used within the TransactionController (REVISAR)
        return new TransferResponse(transaction.getId(), transaction.getAmount(), transaction.getDate(),
                transaction.getType(), transaction.getStatus(), transaction.getDescription());
    }

}
