package print;

import java.sql.*;


public class Ej42 {

    public void imprimirRegistros2(Connection conn, String nombreTabla) {
        String sql = "SELECT * FROM " + nombreTabla;
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // Imprimir cabeceras
            for (int i = 1; i <= cols; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            }
            System.out.println("\n-----------------------------");

            // Iterar filas
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String colName = md.getColumnName(i);
                    if ("CP".equalsIgnoreCase(colName)) {
                        // Intentamos obtener con getInt()
                        int cpInt = rs.getInt(i);
                        if (rs.wasNull()) {
                            System.out.print("NULL\t");
                        } else {
                            System.out.print(cpInt + "\t");
                        }
                    } else {
                        // Para el resto usamos getString() tal como la versión original
                        System.out.print(rs.getString(i) + "\t");
                    }
                }
                System.out.println();
            }

            System.out.println("\n(Nota: getInt devuelve 0 si la columna es NULL. Por eso comprobamos rs.wasNull())");
        } catch (SQLException e) {
            System.err.println("Error en imprimirRegistros2: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
