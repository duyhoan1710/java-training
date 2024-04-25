package com.example.javatraining.services.serviceImpl;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.customer.request.CreateCustomerDto;
import com.example.javatraining.dtos.customer.request.ListCustomerQueryDto;
import com.example.javatraining.dtos.customer.response.CustomerResponse;
import com.example.javatraining.entities.Customer;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.CustomerRepository;
import com.example.javatraining.services.CustomerService;
import com.example.javatraining.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public void createCustomer(CreateCustomerDto payload) {
        Customer customer = this.customerRepository.findByPhone(payload.getPhone()).orElse(null);

        if (customer != null) {
            throw new ErrorException(ErrorCode.DUPLICATE_USER);
        }

        Customer newCustomer = new Customer();

        newCustomer.setName(payload.getName());
        newCustomer.setPhone(payload.getPhone());
        newCustomer.setAddress(payload.getAddress());

        customerRepository.save(newCustomer);
    }

    @Transactional(readOnly = true)
    public ResponsePagination<CustomerResponse> getCustomers(ListCustomerQueryDto query) {
        final Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<Customer> customers = this.customerRepository.findByPhoneLikeAndNameLikeAndAddressLike(Utils.convertKeywordLike(query.getPhone()), Utils.convertKeywordLike(query.getName()), Utils.convertKeywordLike(query.getAddress()), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                customers.getTotalElements(),
                customers.getContent().stream().map(CustomerResponse::from).toList());
    }

    public void deleteCustomer(int customerId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new ErrorException(ErrorCode.USER_NOT_FOUND));

        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerById(long customerId) {
        return this.customerRepository.findById(customerId).orElseThrow(() -> new ErrorException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

}
