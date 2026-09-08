package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.Job;

public interface JobRepository extends JpaRepository<Job, String> {
}

