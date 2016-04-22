package github.crazydais.data.repository;

import java.util.List;
import github.crazydais.data.entity.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findById(Long id);

  List<Customer> findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);
}
