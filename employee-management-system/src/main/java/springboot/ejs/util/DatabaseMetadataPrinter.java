package springboot.ejs.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

//@Component // Spring detectará esto y lo ejecutará al inicio
public class DatabaseMetadataPrinter implements CommandLineRunner {
    @Autowired
    private DataSource dataSource; // Inyectamos la conexión configurada en Paso 2
    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------------------------");
        System.out.println(" INICIO DEL REPORTE DE METADATOS DE BASE DE DATOS");
        System.out.println("----------------------------------------------------------");
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("Usuario: " + connection.getMetaData().getUserName());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("\n📋 TABLAS DETECTADAS EN EL ESQUEMA 'HR':");
// Recuperamos las tablas de tipo "TABLE" (ignorando vistas, etc.)
            ResultSet tables = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
            boolean tablasEncontradas = false;
            while (tables.next()) {
                tablasEncontradas = true;
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(" Tabla encontrada: " + tableName.toUpperCase());
            }
            if (!tablasEncontradas) {
                System.out.println(" No se encontraron tablas. Verifique 'ddl-auto=update'.");
            }
        }
        System.out.println("----------------------------------------------------------");
        System.out.println(" FIN DEL REPORTE");
        System.out.println("----------------------------------------------------------");
    }
}

