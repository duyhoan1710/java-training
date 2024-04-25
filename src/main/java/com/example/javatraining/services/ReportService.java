package com.example.javatraining.services;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.report.request.ProductInventoryReportDto;
import com.example.javatraining.dtos.report.request.RevenueReportDto;
import com.example.javatraining.dtos.report.response.CustomerReportResponse;
import com.example.javatraining.dtos.report.response.ProductInventoryReportResponse;
import com.example.javatraining.dtos.report.response.RevenueReportResponse;

public interface ReportService {
    ResponsePagination<ProductInventoryReportResponse> getProductInventoryReport(ProductInventoryReportDto query);

    RevenueReportResponse getRevenueReport(RevenueReportDto query);

    CustomerReportResponse getCustomerReport();
}
