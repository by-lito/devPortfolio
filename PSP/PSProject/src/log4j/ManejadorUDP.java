package log4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManejadorUDP implements Runnable {

    private static final Logger logger =
            LogManager.getLogger(ManejadorUDP.class);

    private DatagramSocket socket;
    private DatagramPacket paquete;

    public ManejadorUDP(DatagramSocket socket, DatagramPacket paquete) {
        this.socket = socket;
        this.paquete = paquete;
    }

    @Override
    public void run() {
        try {
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());

            logger.info(
                    "Hilo {} procesando mensaje desde {}:{} -> {}",
                    Thread.currentThread().getName(),
                    paquete.getAddress(),
                    paquete.getPort(),
                    mensaje
            );

            Thread.sleep(1000);

            String respuesta = "OK -> " + mensaje;
            byte[] datosRespuesta = respuesta.getBytes();

            DatagramPacket paqueteRespuesta =
                    new DatagramPacket(
                            datosRespuesta,
                            datosRespuesta.length,
                            paquete.getAddress(),
                            paquete.getPort()
                    );

            socket.send(paqueteRespuesta);

            logger.info("Respuesta enviada al cliente");

        } catch (Exception e) {
            logger.error("Error en el manejador UDP", e);
        }
    }
}


