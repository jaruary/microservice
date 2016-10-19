package github.crazydais.data.repository;

import github.crazydais.data.entity.FileEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    FileEntity findById (Long id);

}

