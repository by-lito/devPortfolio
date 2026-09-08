package print;

import java.sql.*;

public class Ej43 {

    public void nombresInvertidos(Connection conn) {
        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS name FROM employees";
        // Pedimos un ResultSet que soporte desplazamiento
        try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {

            // Movemos al final y retrocedemos para imprimir del último al primero
            if (rs.last()) {
                System.out.println("Listado de empleados (de último a primero):");
                do {
                    String nombre = rs.getString("name");
                    System.out.println(nombre);
                } while (rs.previous());
            } else {
                System.out.println("La consulta no devolvió filas.");
            }

        } catch (SQLException e) {
            System.err.println("Error en nombresInvertidos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

