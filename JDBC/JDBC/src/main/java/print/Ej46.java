package print;

import pojos.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Ej46 {

    public void rollback(Connection conn, List<Company> companies) {

        try(PreparedStatement pscreate = conn.prepareStatement("CREATE TABLE IF NOT EXISTS COMPANIES " +
                "( CIF VARCHAR (9) PRIMARY KEY, NOMBRE VARCHAR (100) NOT NULL, SECTOR VARCHAR (100) NOT NULL)");
        PreparedStatement psinsert = conn.prepareStatement("INSERT INTO COMPANIES (CIF, NOMBRE, SECTOR) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE NOMBRE = VALUES(NOMBRE), SECTOR = VALUES(SECTOR)"
                );){

            conn.setAutoCommit(false);

            pscreate.executeUpdate();

            for (Company c : companies) {
                psinsert.setString(1, c.getCif());
                psinsert.setString(2, c.getNombre());
                psinsert.setString(3, c.getSector());
                psinsert.addBatch();
            }

            System.out.println("Ejecutando el lote de inserciones...");
            psinsert.executeBatch();


            conn.commit();
            System.out.println("¡Éxito! La transacción ha sido confirmada (commit).");


        } catch (SQLException e) {

            if(conn != null){

                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("se ha hecho un rollback");
            }

            throw new RuntimeException(e);

        }finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
