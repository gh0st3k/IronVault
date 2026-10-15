package ghostek.ironvault.customer;

import ghostek.ironvault.customer.dto.CustomerCreateRequest;
import ghostek.ironvault.customer.dto.CustomerCreateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerCreateResponse registryCustomer(CustomerCreateRequest request){
        Customer customer = createCustomer(request);

        customerRepository.save(customer);
        return toResponse(customer);
    }

    @Transactional
    protected Customer createCustomer(CustomerCreateRequest request){
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setUsername(request.username());
        // Hash the password before saving
        customer.setPassword(passwordEncoder.encode(request.password()));
        // Explicitly generate the timestamp on the secure server side
        customer.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        customer.setFirstName(request.firstName());
        customer.setSecondName(request.secondName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setBirthDate(request.birthDate());

        return customer;
    }

    private CustomerCreateResponse toResponse(Customer customer){
        return new CustomerCreateResponse(customer.getId(), customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getSecondName(),
                customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getBirthDate());
    }

    private byte[] generateSalt16Byte() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }
}
