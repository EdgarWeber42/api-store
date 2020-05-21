package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
