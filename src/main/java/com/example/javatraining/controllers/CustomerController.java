package com.example.javatraining.controllers;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.customer.request.CreateCustomerDto;
import com.example.javatraining.dtos.customer.request.ListCustomerQueryDto;
import com.example.javatraining.dtos.customer.response.CustomerResponse;
import com.example.javatraining.services.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RestController
public class CustomerController {
    private CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @PostMapping("customers")
    void createCustomer(CreateCustomerDto payload) {
        customerService.createCustomer(payload);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @GetMapping("customers")
    ResponsePagination<CustomerResponse> getCustomers(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false, defaultValue = "") String phone,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String address
    ) {
        return customerService.getCustomers(new ListCustomerQueryDto(page, limit, phone, name, address));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("customers/{customerId}")
    void deleteCustomer(@PathVariable(required = true) int customerId) {
        customerService.deleteCustomer(customerId);
    }
}
