package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer c);

    List<Customer> findAll();

    Customer findById(Long id);

}
