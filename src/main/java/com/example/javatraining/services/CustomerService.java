package com.example.javatraining.services;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.customer.request.CreateCustomerDto;
import com.example.javatraining.dtos.customer.request.ListCustomerQueryDto;
import com.example.javatraining.dtos.customer.response.CustomerResponse;

public interface CustomerService {
    void createCustomer(CreateCustomerDto payload);

    ResponsePagination<CustomerResponse> getCustomers(ListCustomerQueryDto query);

    void deleteCustomer(int customerId);
}
