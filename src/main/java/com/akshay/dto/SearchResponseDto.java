package com.akshay.dto;

import lombok.Data;

@Data
public class SearchResponseDto {

    private String personName;

    private Long personMobileNo;

    private String personEmail;

    private Character personGender;

    private Long personSsn;
}
