package github.crazydais.data.repository;

import java.util.List;
import github.crazydais.data.entity.CustomerEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  CustomerEntity findById(Long id);

  List<CustomerEntity> findByFirstName(String firstName);

  List<CustomerEntity> findByLastName(String lastName);
}
