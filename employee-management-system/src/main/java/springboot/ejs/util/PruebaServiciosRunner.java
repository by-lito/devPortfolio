package springboot.ejs.util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springboot.ejs.dominio.Employee;
import springboot.ejs.dominio.Job;
import springboot.ejs.repositorios.JobRepository;
import springboot.ejs.servicios.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
//@Component
public class PruebaServiciosRunner implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final JobRepository jobRepository; // Auxiliar para crear datos válidos
    // Inyectamos el SERVICIO, no el repositorio de empleados.
    public PruebaServiciosRunner(EmployeeService employeeService, JobRepository jobRepository) {
        this.employeeService = employeeService;
        this.jobRepository = jobRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------------------------");
        System.out.println(" INICIO DE PRUEBAS DE CAPA DE SERVICIO");
        System.out.println("----------------------------------------------------------");
// 1. PRUEBA DE INSERCIÓN (CREATE)
// Necesitamos un Job existente (asegúrate de que exista 'IT_PROG' o crea uno dummy si la BD está vacía)
        Job puesto = new Job();
        puesto.setId("AD_PRES"); // Usamos uno común del esquema HR
        puesto.setJobTitle("President");
        puesto.setMinSalary(20000.0);
        puesto.setMaxSalary(40000.0);
        jobRepository.save(puesto); // Lo guardamos/actualizamos por si acaso
        Employee nuevoEmp = new Employee();
        nuevoEmp.setFirstName("TestService");
        nuevoEmp.setLastName("User");
        nuevoEmp.setEmail("test.service@example.com");
        nuevoEmp.setHireDate(LocalDate.now());
        nuevoEmp.setJob(puesto);
        nuevoEmp.setSalary(new BigDecimal("25000"));
        System.out.println("Intentando insertar empleado mediante servicio...");
        Employee empGuardado = employeeService.insertar(nuevoEmp);
        System.out.println("Empleado guardado con ID: " + empGuardado.getId());
// 2. PRUEBA DE CONSULTA DE NEGOCIO (READ)
        System.out.println("\n Probando búsqueda VIP (> 20,000)...");
        List<Employee> vips = employeeService.buscarVip(new BigDecimal("20000"));
        vips.forEach(e -> System.out.println(" VIP: " + e.getFirstName() + " " + e.getLastName()));
// 3. PRUEBA DE TRANSACCIÓN Y ACTUALIZACIÓN (UPDATE)
        System.out.println("\nActualizando salario...");
        empGuardado.setSalary(new BigDecimal("30000"));
        Employee empActualizado = employeeService.actualizar(empGuardado.getId(), empGuardado);
        System.out.println(" Salario actualizado a: " + empActualizado.getSalary());
// 4. PRUEBA DE BORRADO (DELETE)
        System.out.println("\nBorrando empleado de prueba...");
        employeeService.borrar(empGuardado.getId());
        System.out.println("Empleado borrado correctamente.");
        System.out.println("----------------------------------------------------------");
        System.out.println("FIN DE PRUEBAS DE SERVICIO");
        System.out.println("----------------------------------------------------------");
    }
}
