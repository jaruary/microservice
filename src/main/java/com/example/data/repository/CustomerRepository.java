package com.example.data.repository;

import java.util.List;
import com.example.data.entity.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer getOneById(Long id);

  List<Customer> findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);
}
