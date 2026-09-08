package springboot.ejs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ejs.dominio.Employee;
import springboot.ejs.servicios.EmployeeService;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET ALL
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.consultarTodos();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.consultarPorId(id);

        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Employee newEmployee = employeeService.insertar(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeDetails) {

        try {
            Employee updatedEmployee = employeeService.actualizar(id, employeeDetails);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        try {
            employeeService.borrar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // VIP SEARCH
    @GetMapping("/search/vip")
    public List<Employee> searchVip(@RequestParam BigDecimal minSalary) {
        return employeeService.buscarVip(minSalary);
    }
}
