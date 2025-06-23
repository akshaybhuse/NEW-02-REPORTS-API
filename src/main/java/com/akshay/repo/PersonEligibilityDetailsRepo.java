package com.akshay.repo;

import com.akshay.entity.PersonEligibilityDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonEligibilityDetailsRepo extends JpaRepository<PersonEligibilityDetailsEntity, Integer> {

    @Query("select distinct (planName) from PersonEligibilityDetailsEntity")
    public List<String> findUniquePlanNames();

    @Query("select distinct (planStatus) from PersonEligibilityDetailsEntity")
    public List<String> findUniquePlanStatuses();

}
