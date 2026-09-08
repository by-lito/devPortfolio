package print;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ej45 {

    public void printClientesDni(Connection conn, String[] dnis) {
//try con resources para el prepared statement, podemos guardar una variable para la sentencia pero yo la he metido directamente
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM clientes WHERE DNI = ?");) {
            for(String dni : dnis) {

                ps.setString(1, dni);


                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getString("CP") + "  " + rs.getString("APELLIDOS"));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Error al obtener los datos del dni");
        }


    }


}
