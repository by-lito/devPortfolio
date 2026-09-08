package print;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ej47 {

    public void getApellidosFromDni(Connection conn,String dni){

        try(PreparedStatement ps = conn.prepareStatement("SELECT obtener_apellidos_por_dni(?)")){

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()){

                if(rs.next()){

                    System.out.println("El apellido del DNI "+ dni + " es " + rs.getString(1));

                }else{

                    System.out.println("El apellido del DNI "+ dni + " no existe prueba con 33333333E");

                }

            }

        }
        catch (SQLException e){
            System.err.println(e);
        }

    }

}
