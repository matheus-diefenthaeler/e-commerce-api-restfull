package br.com.diefenthaeler.e_comerce_api.presentation.api;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.CreateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.PagedResponse;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.CreateCustomerUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.GetCustomerByIdUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.ListCustomersUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final ListCustomersUseCase listCustomersUseCase;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CustomerResponse response = createCustomerUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse response = getCustomerByIdUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<CustomerResponse>> listCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedResponse<CustomerResponse> response = listCustomersUseCase.execute(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}