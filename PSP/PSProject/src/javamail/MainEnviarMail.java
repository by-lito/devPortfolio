package javamail;

public class MainEnviarMail {

    public static void main(String[] args) {

        // ⚠️ CHANGE THESE VALUES
        String smtpHost = "smtp.gmail.com";   // or smtp.gmail.com
        String puerto = "587";

        String emailEmisor = "angelmi201130@gmail.com";
        String password = "";
        String emailDestino = "angel.miguelflp@gmail.com";

        String asunto = "Correo de prueba JavaMail";
        String mensaje = "Este correo ha sido enviado usando JavaMail y SMTP.";

        GestorEmail gestor = new GestorEmail();

        try {
            gestor.enviarEmailTexto(
                    smtpHost,
                    puerto,
                    emailEmisor,
                    password,
                    emailDestino,
                    asunto,
                    mensaje
            );

            System.out.println("Correo enviado correctamente");

        } catch (Exception e) {
            System.out.println("Error enviando el correo");
            e.printStackTrace();
        }
    }
}
