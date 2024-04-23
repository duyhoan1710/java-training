package com.example.javatraining.controllers;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.customer.request.CreateCustomerDto;
import com.example.javatraining.dtos.customer.request.ListCustomerQueryDto;
import com.example.javatraining.dtos.customer.response.CustomerResponse;
import com.example.javatraining.services.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@Value
@SecurityRequirement(name = "bearerAuth")
@RestController
public class CustomerController {
    CustomerService customerService;

    @PostMapping("customers")
    void createCustomer(CreateCustomerDto payload) {
        customerService.createCustomer(payload);
    }

    @GetMapping("customers")
    ResponsePagination<CustomerResponse> getCustomers(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address
    ) {
        return customerService.getCustomers(new ListCustomerQueryDto(page, limit, phone, name, address));
    }

    @DeleteMapping("customers/{customerId}")
    void deleteCustomer(@PathVariable(required = true) int customerId) {
        customerService.deleteCustomer(customerId);
    }
}
