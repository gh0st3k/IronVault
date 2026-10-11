package ghostek.ironvault.customer.dto;

import java.time.ZonedDateTime;

public record CustomerCreateRequest(String id, String firstName, String secondName, String email, String phone, String address, ZonedDateTime birthDate) {
}
