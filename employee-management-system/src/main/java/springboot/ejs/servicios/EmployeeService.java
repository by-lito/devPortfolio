package springboot.ejs.servicios;


import springboot.ejs.dominio.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> consultarTodos();

    Optional<Employee> consultarPorId(Long id);

    Employee insertar(Employee employee);

    Employee actualizar(Long id, Employee employeeDetails);

    void borrar(Long id);

    List<Employee> buscarVip(BigDecimal minSalary);

    List<Employee> buscarEmpleadosVip(BigDecimal bigDecimal);
}
