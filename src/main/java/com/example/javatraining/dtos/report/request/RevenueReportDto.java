package com.example.javatraining.dtos.report.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RevenueReportDto {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
