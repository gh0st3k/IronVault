package ghostek.ironvault.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Random since not everybody has DNI, but also NIE, Passport,...

    @NotBlank(message = "DNI, NIE or Passport is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    private String password; // Not a column since we are going to store the hash

    @Transient
    private String confirmPassword;

    @CreatedDate // Automatically sets the time on save via Spring Data JPA
    @Column(name = "password_created_at", nullable = false, updatable = true)
    private OffsetDateTime createdAt;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Second name is required")
    @Column(nullable = false)
    private String secondName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email; //we have to check the format with Regex

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Birth date is required")
    @Column(nullable = false)
    private ZonedDateTime birthDate;
}
