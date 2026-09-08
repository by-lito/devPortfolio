package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ejs.dominio.Employee;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // --- MÉTODOS DE CONSULTA DERIVADOS (QUERY METHODS) ---
// Spring analiza "findBy" + "Salary" + "GreaterThan".
// SQL Generado aprox: SELECT * FROM employees WHERE salary > ?
    List<Employee> findBySalaryGreaterThan(BigDecimal salarioMinimo);
    List<Employee> findAllByOrderByLastNameAsc();
    List<Employee> findBySalaryGreaterThanEqual(BigDecimal minSalary);
    // Podemos concatenar condiciones con 'And', 'Or'.
// SQL: SELECT * FROM employees WHERE department_id IS NULL
    List<Employee> findByDepartmentIsNull();
    // SQL: SELECT * FROM employees WHERE last_name LIKE '%texto%'
    List<Employee> findByLastNameContaining(String texto);
}
