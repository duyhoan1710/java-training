package com.example.javatraining.services.serviceImpl;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.order.response.CustomerReport;
import com.example.javatraining.dtos.report.request.ProductInventoryReportDto;
import com.example.javatraining.dtos.report.request.RevenueReportDto;
import com.example.javatraining.dtos.report.response.CustomerReportResponse;
import com.example.javatraining.dtos.report.response.ProductInventoryReportResponse;
import com.example.javatraining.dtos.report.response.RevenueReportResponse;
import com.example.javatraining.entities.Customer;
import com.example.javatraining.entities.Product;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.CustomerRepository;
import com.example.javatraining.repositories.OrderRepository;
import com.example.javatraining.repositories.ProductRepository;
import com.example.javatraining.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    public ResponsePagination<ProductInventoryReportResponse> getProductInventoryReport(ProductInventoryReportDto query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<Product> products = productRepository.findByPriceLessThanEqual(query.getInventory(), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                products.getTotalElements(),
                products.getContent().stream().map(ProductInventoryReportResponse::from).toList());
    }

    public RevenueReportResponse getRevenueReport(RevenueReportDto query) {
        double revenue = orderRepository.getRevenueReport(query.getFromDate(), query.getToDate()).orElse(0.0);

        return new RevenueReportResponse(revenue);
    }

    public CustomerReportResponse getCustomerReport() {
        LocalDateTime toDate = LocalDateTime.now();
        LocalDateTime fromDate = toDate.minusDays(1);

        CustomerReport customerReport = orderRepository.getCustomerReport(fromDate, toDate).orElse(null);

        if (customerReport == null) return null;

        Customer customer = customerRepository.findById(customerReport.getCustomerId()).orElse(null);

        if (customer == null) throw new ErrorException(ErrorCode.CUSTOMER_NOT_FOUND);

        return CustomerReportResponse.from(customer, customerReport.getTotalMoney());
    }
}
