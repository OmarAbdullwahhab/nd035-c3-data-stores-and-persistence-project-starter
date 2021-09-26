package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer save(Customer c)
    {
        return this.repository.save(c);
    }

    @Override
    public List<Customer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return this.repository.findById(id).get();
    }


}
