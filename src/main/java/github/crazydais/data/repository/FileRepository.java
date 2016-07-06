package github.crazydais.data.repository;

import github.crazydais.data.entity.File;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface FileRepository extends JpaRepository<File, Long> {

  File findById(Long id);

}

