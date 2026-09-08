package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.JobHistory;
import springboot.ejs.dominio.JobHistoryId;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {
}

