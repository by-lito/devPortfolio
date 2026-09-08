package springboot.ejs.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.ejs.dominio.Employee;
import springboot.ejs.excepciones.RecursoNoEncontradoException;
import springboot.ejs.repositorios.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> consultarTodos() {

        logger.info("Solicitando listado completo de empleados");

        List<Employee> lista = employeeRepository.findAll();

        logger.info("Se han recuperado {} empleados", lista.size());

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> consultarPorId(Long id) {

        logger.info("Buscando empleado con ID: {}", id);

        return employeeRepository.findById(id);
    }

    @Override
    public Employee insertar(Employee employee) {

        logger.info("Intentando insertar empleado: {} {}",
                employee.getFirstName(),
                employee.getLastName());

        try {
            Employee guardado = employeeRepository.save(employee);
            logger.info("Empleado insertado correctamente con ID: {}",
                    guardado.getId());
            return guardado;
        } catch (Exception e) {
            logger.error("Error al insertar empleado en la base de datos", e);
            throw e;
        }
    }

    @Override
    public Employee actualizar(Long id, Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Empleado no encontrado con ID: " + id));

        logger.info("Actualizando empleado con ID: {}", id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setSalary(employeeDetails.getSalary());
        employee.setCommissionPct(employeeDetails.getCommissionPct());
        employee.setJob(employeeDetails.getJob());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setManager(employeeDetails.getManager());

        return employeeRepository.save(employee);
    }

    @Override
    public void borrar(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No existe el empleado con ID: " + id);
        }

        logger.info("Borrando empleado con ID: {}", id);

        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> buscarVip(BigDecimal minSalary) {

        logger.info("Buscando empleados con salario mayor o igual a {}",
                minSalary);

        return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
    }

    @Override
    public List<Employee> buscarEmpleadosVip(BigDecimal bigDecimal) {

        logger.info("Método buscarEmpleadosVip invocado");

        return employeeRepository.findBySalaryGreaterThanEqual(bigDecimal);
    }
}
