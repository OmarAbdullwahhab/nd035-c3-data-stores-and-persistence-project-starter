package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
