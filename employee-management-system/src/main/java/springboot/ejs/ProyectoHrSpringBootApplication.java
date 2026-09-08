package springboot.ejs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// EXCLUSIÓN TEMPORAL (NIVEL EXPERTO):
// 1. DataSourceAutoConfiguration: Evita error al no tener URL de Base de datos aún.
// 2. SecurityAutoConfiguration: Evita que nos pida usuario/clave para ver el HTML de prueba.
// NOTA: Eliminaremos estos "exclude" en los pasos siguientes al configurar el sistema real.
@SpringBootApplication
public class ProyectoHrSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoHrSpringBootApplication.class, args);
    }
}
