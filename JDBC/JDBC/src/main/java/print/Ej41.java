package print;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class Ej41 {

    public void reescribirTabla(Connection conn, List<String[]> datosFinales){

        try (PreparedStatement pstmtdel = conn.prepareStatement("DELETE FROM CLIENTES ");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES (?,?,?)");) {

            pstmtdel.executeUpdate();

            for (String[] cliente : datosFinales){
                String dni = cliente[0];
                String apellidos = cliente[1];
                String cP = cliente[2];

                System.out.println("PROCESANDO CLIENTE: " + dni);

                pstmt.setString(1,dni);
                pstmt.setString(2,apellidos);
                if(cP==null){
                    pstmt.setNull(3, Types.VARCHAR);
                }else {
                    pstmt.setString(3,cP);
                }
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
