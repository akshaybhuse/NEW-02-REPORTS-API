package com.akshay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Tbl_Person_Eligibility")
public class PersonEligibilityDetailsEntity {

    @Id
    private Integer eligibilityDetailsId;

    private String personName;

    private Long mobileNo;

    private String personEmail;

    private Character personGender;

    private Long personSsn;

    private String planName;

    private String planStatus;

    private LocalDate planStartDate;

    private LocalDate planEndDate;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdBy;

    private String updatedBy;

}
