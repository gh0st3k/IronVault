package ghostek.ironvault.customer.dto;

import java.time.ZonedDateTime;

public record CustomerCreateRequest(Long id, String username, String password, String firstName, String secondName, String email, String phone, String address, ZonedDateTime birthDate) {
}
