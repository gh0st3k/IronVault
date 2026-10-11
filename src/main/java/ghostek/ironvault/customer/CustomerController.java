package ghostek.ironvault.customer;

import ghostek.ironvault.customer.dto.CustomerCreateRequest;
import ghostek.ironvault.customer.dto.CustomerCreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class will handle incoming HTTP requests (like GET, POST,...)

// @RestController = @Controller + @ResponseBody
// @Controller: Marks the class as a web request handler component in the Spring context
// @ResponseBody: Tells Spring that the data returned by every method in the class should be
// written into the HTTP response body
@RestController
// RequestMapping supports all HTTP methods, whereas GetMapping only GET
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // PostMapping maps POST request to /api/customers
    // Content-Type: application/json
    @PostMapping
    public CustomerCreateResponse createCustomer(@RequestBody CustomerCreateRequest request){ //@RequestBody is used
        return customerService.registryCustomer(request);
    }
}
