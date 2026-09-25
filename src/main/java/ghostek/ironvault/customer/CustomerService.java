package ghostek.ironvault.customer;

import ghostek.ironvault.customer.dto.CustomerCreateRequest;
import ghostek.ironvault.customer.dto.CustomerCreateResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerCreateResponse registryCustomer(CustomerCreateRequest request){
        Customer customer = createCustomer(request);

        customerRepository.save(customer);
        return toResponse(customer);
    }

    private Customer createCustomer(CustomerCreateRequest request){
        Customer customer = new Customer();
        customer.setDNI(request.DNI());
        customer.setFirstName(request.firstName());
        customer.setSecondName(request.secondName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setBirthDate(request.birthDate());

        return customer;
    }

    private CustomerCreateResponse toResponse(Customer customer){
        return new CustomerCreateResponse(customer.getDNI(), customer.getFirstName(), customer.getSecondName(),
                customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getBirthDate());
    }

}
