package com.akshay.runner;
import java.time.LocalDate;

import com.akshay.entity.PersonEligibilityDetailsEntity;
import com.akshay.repo.PersonEligibilityDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PersonEligibilityDetailsRepo repo;

    @Override
    public void run(String... args) throws Exception {

        PersonEligibilityDetailsEntity entity=new PersonEligibilityDetailsEntity();
        entity.setEligibilityDetailsId(1);
        entity.setPersonName("John");
        entity.setPersonMobileNo(8899778822L);
        entity.setPersonEmail("john@gmail.com");
        entity.setPersonGender('M');
        entity.setPersonSsn(24356791L);
        entity.setPlanName("SNAP");
        entity.setPlanStatus("Approved");
        entity.setPlanStartDate(LocalDate.now());
        entity.setPlanEndDate(LocalDate.of(2026, 5, 11));
        entity.setCreatedDate(LocalDate.now());
        entity.setUpdatedDate(LocalDate.now());
        entity.setCreatedBy("Akshay");
        entity.setUpdatedBy("");
        repo.save(entity);

        PersonEligibilityDetailsEntity entity2=new PersonEligibilityDetailsEntity();
        entity2.setEligibilityDetailsId(2);
        entity2.setPersonName("Mark");
        entity2.setPersonMobileNo(9955411974L);
        entity2.setPersonEmail("mark@gmail.com");
        entity2.setPersonGender('M');
        entity2.setPersonSsn(6655118896L);
        entity2.setPlanName("CCAP");
        entity2.setPlanStatus("Denied");
        entity2.setPlanStartDate(LocalDate.now());
        entity2.setPlanEndDate(LocalDate.now().plusYears(1));
        entity2.setCreatedDate(LocalDate.now());
        entity2.setUpdatedDate(LocalDate.now());
        entity2.setCreatedBy("Osho");
        entity2.setUpdatedBy("");
        repo.save(entity2);

        PersonEligibilityDetailsEntity entity3=new PersonEligibilityDetailsEntity();
        entity3.setEligibilityDetailsId(3);
        entity3.setPersonName("Jasmin");
        entity3.setPersonMobileNo(9954389200L);
        entity3.setPersonEmail("jasmin@gmail.com");
        entity3.setPersonGender('F');
        entity3.setPersonSsn(8876541119L);
        entity3.setPlanName("MEDICAID");
        entity3.setPlanStatus("Renew");
        entity3.setPlanStartDate(LocalDate.now());
        entity3.setPlanEndDate(LocalDate.now().plusMonths(15));
        entity3.setCreatedDate(LocalDate.now());
        entity3.setUpdatedDate(LocalDate.now());
        entity3.setCreatedBy("Maxwell");
        entity3.setUpdatedBy("");
        repo.save(entity3);

        PersonEligibilityDetailsEntity entity4=new PersonEligibilityDetailsEntity();
        entity4.setEligibilityDetailsId(4);
        entity4.setPersonName("Juli");
        entity4.setPersonMobileNo(9931232544L);
        entity4.setPersonEmail("juli@gmail.com");
        entity4.setPersonGender('F');
        entity4.setPersonSsn(8812231800L);
        entity4.setPlanName("MEDICARE");
        entity4.setPlanStatus("Closed");
        entity4.setPlanStartDate(LocalDate.of(2022, 1, 21));
        entity4.setPlanEndDate(LocalDate.now());
        entity4.setCreatedDate(LocalDate.of(2022, 1, 20));
        entity4.setUpdatedDate(LocalDate.now());
        entity4.setCreatedBy("Rony");
        entity4.setUpdatedBy("Conway");
        repo.save(entity4);






    }
}
