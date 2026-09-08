package springboot.ejs.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springboot.ejs.dominio.Employee;
import springboot.ejs.repositorios.EmployeeRepository;

import java.util.List;

//@Component
public class AngelRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public AngelRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("\n==== EMPLEADOS ORDENADOS POR APELLIDO ====\n");

        List<Employee> employees = employeeRepository.findAllByOrderByLastNameAsc();

        for (Employee e : employees) {
            System.out.println(
                    e.getLastName() + ", " +
                            e.getFirstName() +
                            " | Email: " + e.getEmail()
            );
        }

        System.out.println("\n==========================================\n");
    }
}

