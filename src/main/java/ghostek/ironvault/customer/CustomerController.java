package ghostek.ironvault.customer;

import ghostek.ironvault.customer.dto.CustomerCreateRequest;
import ghostek.ironvault.customer.dto.CustomerCreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerCreateResponse createCustomer(@RequestBody CustomerCreateRequest request){ //@RequestBody is used
        return customerService.registryCustomer(request);
    }
}
