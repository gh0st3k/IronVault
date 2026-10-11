package ghostek.ironvault.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private String id; // It can be DNI, NIE, Passport,...

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String email; //we have to check the format with Regex

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private ZonedDateTime birthDate;
}
