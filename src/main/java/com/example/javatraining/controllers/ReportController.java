package com.example.javatraining.controllers;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.report.request.ProductInventoryReportDto;
import com.example.javatraining.dtos.report.request.RevenueReportDto;
import com.example.javatraining.dtos.report.response.CustomerReportResponse;
import com.example.javatraining.dtos.report.response.ProductInventoryReportResponse;
import com.example.javatraining.dtos.report.response.RevenueReportResponse;
import com.example.javatraining.services.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;

    @GetMapping("reports/product-inventory")
    ResponsePagination<ProductInventoryReportResponse> productInventoryReport(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = true) int inventory) {
        ProductInventoryReportDto productInventoryReportDto = new ProductInventoryReportDto();
        productInventoryReportDto.setPage(page);
        productInventoryReportDto.setLimit(limit);
        productInventoryReportDto.setInventory(inventory);

        return this.reportService.getProductInventoryReport(productInventoryReportDto);
    }

    @GetMapping("reports/revenue")
    RevenueReportResponse revenueReport(
            @RequestParam(required = true) LocalDateTime fromDate,
            @RequestParam(required = true) LocalDateTime toDate
    ) {
        RevenueReportDto revenueReportDto = new RevenueReportDto();
        revenueReportDto.setFromDate(fromDate);
        revenueReportDto.setToDate(toDate);

        return this.reportService.getRevenueReport(revenueReportDto);
    }

    @GetMapping("reports/customer")
    CustomerReportResponse customerReport(
    ) {
        return this.reportService.getCustomerReport();
    }
}
