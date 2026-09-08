package javamail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class GestorEmail {

    private Properties propiedades;
    private Session sesion;

    /* 1. Configure SMTP server */
    private void setPropiedadesServidorSMTP(String smtpHost, String puerto) {
        propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", smtpHost);
        propiedades.put("mail.smtp.port", puerto);

        sesion = Session.getInstance(propiedades);
    }

    /* 2. Connect to SMTP server */
    private Transport conectarServidorSMTP(String email, String password)
            throws MessagingException {

        Transport transport = sesion.getTransport("smtp");
        transport.connect(
                propiedades.getProperty("mail.smtp.host"),
                email,
                password
        );
        return transport;
    }

    /* 3. Create base message */
    private Message crearNucleoMensaje(String emisor,
                                       String destinatario,
                                       String asunto)
            throws MessagingException {

        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(emisor));
        mensaje.setRecipient(Message.RecipientType.TO,
                new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        return mensaje;
    }

    /* 4. Create text message */
    private Message crearMensajeTexto(String emisor,
                                      String destinatario,
                                      String asunto,
                                      String textoMensaje)
            throws MessagingException {

        Message mensaje = crearNucleoMensaje(emisor, destinatario, asunto);
        mensaje.setText(textoMensaje);
        return mensaje;
    }

    /* 5. Create message with attachment */
    private Message crearMensajeConAdjunto(String emisor,
                                           String destinatario,
                                           String asunto,
                                           String textoMensaje,
                                           String rutaFichero)
            throws MessagingException, IOException {

        Message mensaje = crearNucleoMensaje(emisor, destinatario, asunto);

        // Body text
        MimeBodyPart texto = new MimeBodyPart();
        texto.setText(textoMensaje);

        // Attachment
        MimeBodyPart adjunto = new MimeBodyPart();
        adjunto.attachFile(new File(rutaFichero));

        // Multipart
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(texto);
        multipart.addBodyPart(adjunto);

        mensaje.setContent(multipart);
        return mensaje;
    }

    /* 6. Public method to send email */
    public void enviarEmailTexto(String smtpHost,
                                 String puerto,
                                 String emailEmisor,
                                 String password,
                                 String emailDestino,
                                 String asunto,
                                 String mensajeTexto)
            throws MessagingException {

        setPropiedadesServidorSMTP(smtpHost, puerto);
        Transport transport = conectarServidorSMTP(emailEmisor, password);

        Message mensaje = crearMensajeTexto(
                emailEmisor,
                emailDestino,
                asunto,
                mensajeTexto
        );

        transport.sendMessage(mensaje, mensaje.getAllRecipients());
        transport.close();
    }
}
