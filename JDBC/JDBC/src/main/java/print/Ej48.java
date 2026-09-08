package print;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Ej48 {

    public void navegarTabla(Connection conn, String tableName) {
        String sql = "SELECT * FROM " + tableName;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Statement stmt = conn.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY
             );
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.last()) {
                System.out.println("La consulta no devolvió filas. Saliendo.");
                return;
            }

            int totalFilas = rs.getRow();
            rs.first();
            mostrarFila(rs, totalFilas);

            String comando;
            System.out.println("Introduce comando (k=siguiente, d=anterior, N=ir a N, .=salir):");

            while ((comando = br.readLine()) != null) {
                comando = comando.trim();

                switch (comando) {

                    case ".":  // Salir
                        System.out.println("Comando de salida recibido. Terminando.");
                        return;

                    case "k": case "K":  // Siguiente fila
                        moverSiguiente(rs, totalFilas);
                        break;

                    case "d": case "D":  // Fila anterior
                        moverAnterior(rs);
                        break;

                    default:
                        // Intentar interpretar como número
                        if (!moverAIndice(rs, comando, totalFilas)) {
                            System.out.println("Comando no reconocido: '" + comando + "'");
                            System.out.println("Usa k, d, ., o un número entero.");
                        }
                        break;
                }

                System.out.println("Introduce comando (k=siguiente, d=anterior, N=ir a N, .=salir):");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private void moverSiguiente(ResultSet rs, int totalFilas) throws SQLException {
        if (rs.isLast()) {
            System.out.println("Ya estás en la última fila (" + totalFilas + ").");
            return;
        }
        if (rs.next()) {
            mostrarFila(rs, totalFilas);
        }
    }

    private void moverAnterior(ResultSet rs) throws SQLException {
        if (rs.isFirst()) {
            System.out.println("Ya estás en la primera fila (1).");
            return;
        }
        if (rs.previous()) {
            mostrarFila(rs, rs.getRow());
        }
    }

    private boolean moverAIndice(ResultSet rs, String comando, int totalFilas) throws SQLException {
        try {
            int fila = Integer.parseInt(comando);

            if (fila < 1 || fila > totalFilas) {
                System.out.println("Fila inválida. Debe estar entre 1 y " + totalFilas + ".");
                return true; // comando válido, aunque fuera de rango
            }

            if (rs.absolute(fila)) {
                mostrarFila(rs, totalFilas);
            } else {
                System.out.println("No se pudo mover a la fila " + fila + ".");
            }
            return true; // comando numérico válido

        } catch (NumberFormatException e) {
            return false; // no era número → lo manejará el switch como comando desconocido
        }
    }

    private void mostrarFila(ResultSet rs, int totalRows) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();
        int filaActual = rs.getRow();

        System.out.println("----- fila " + filaActual + " de " + totalRows + " -----");

        for (int i = 1; i <= cols; i++) {
            String nombre = md.getColumnLabel(i);
            Object valor = rs.getObject(i);
            System.out.println(nombre + " : " + (valor == null ? "NULL" : valor.toString()));
        }

        System.out.println("------------------------------");
    }
}

