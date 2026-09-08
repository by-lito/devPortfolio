package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

