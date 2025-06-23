package com.akshay.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequestDto {

    private String planName;

    private String planStatus;

    private LocalDate planStartDate;

    private LocalDate planEndDate;
}
