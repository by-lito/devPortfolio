package print;

import java.sql.*;


public class Ej44 {


    public int contarFilas(Connection conn, String sql) {
        try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.last()) {
                // No hay filas
                System.out.println("La consulta no devolvió filas.");
                return 0;
            }
            int filas = rs.getRow();
            System.out.println("Número de filas (sin iterar): " + filas);
            // Opcional: volver al inicio si luego se quiere leer (rs.beforeFirst())
            rs.beforeFirst();
            return filas;
        } catch (SQLException e) {
            System.err.println("Error en contarFilas: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}

