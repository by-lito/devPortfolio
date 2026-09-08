package springboot.ejs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springboot.ejs.dominio.Employee;
import springboot.ejs.repositorios.EmployeeRepository;
import springboot.ejs.repositorios.RegionRepository;

import java.math.BigDecimal;
import java.util.List;
//@Component // Marca esta clase como un Bean de Spring para que se ejecute al inicio
public class PruebaRepositoriosRunner implements CommandLineRunner {
    // @Autowired: INYECCIÓN DE DEPENDENCIAS
// Le pedimos a Spring que nos busque la implementación de estos repositorios
// y nos la "inyecte" aquí. Nosotros nunca hacemos "new RegionRepository()".
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------------------------");
        System.out.println("🧪 INICIO DE PRUEBA DE REPOSITORIOS (JPA)");
        System.out.println("----------------------------------------------------------");
// PRUEBA 1: Usar un método estándar de JpaRepository (count)
        long totalRegiones = regionRepository.count();
        System.out.println("✅ Total de Regiones encontradas: " + totalRegiones);
// PRUEBA 2: Usar findAll() para imprimir datos
        System.out.println("\n📋 Listado de Regiones:");
        regionRepository.findAll().forEach(region ->
                System.out.println(" -> " + region.toString())
        );
// PRUEBA 3: Usar un Query Method personalizado (Salario > 10000)
        BigDecimal salarioCorte = new BigDecimal(10000);
        System.out.println("\n💰 Empleados con salario mayor a " + salarioCorte + ":");
        List<Employee> ricos = employeeRepository.findBySalaryGreaterThan(salarioCorte);
        if(ricos.isEmpty()) {
            System.out.println(" (No hay empleados con ese salario, verifica tus datos)");
        } else {
            ricos.forEach(emp ->
                    System.out.println(" -> " + emp.getFirstName() + " " + emp.getLastName() +
                            " [Salario: " + emp.getSalary() + "]")
            );
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("🧪 FIN DE PRUEBA");
        System.out.println("----------------------------------------------------------");
    }
}