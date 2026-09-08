package log4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UDPServerThreadPoolWithLogger {
    private static final int PUERTO = 9876;
    private static final int TAM_POOL = 5;
    private static final Logger logger = LogManager.getLogger(UDPServerThreadPoolWithLogger.class);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(TAM_POOL);

        try {
            Throwable var2 = null;
            Object var3 = null;

            try {
                DatagramSocket socket = new DatagramSocket(9876);

                try {
                    logger.info("Servidor UDP iniciado en el puerto {}", 9876);

                    while(true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);
                        threadPool.execute(new ManejadorUDP(socket, copiarPaquete(paquete)));
                    }
                } finally {
                    if (socket != null) {
                        socket.close();
                    }

                }
            } catch (Throwable var21) {
                if (var2 == null) {
                    var2 = var21;
                } else if (var2 != var21) {
                    var2.addSuppressed(var21);
                }

                throw var2;
            }
        } catch (Throwable e) {
            logger.error("Error en el servidor UDP", e);
        } finally {
            threadPool.shutdown();
        }

    }

    private static DatagramPacket copiarPaquete(DatagramPacket paquete) {
        byte[] datos = new byte[paquete.getLength()];
        System.arraycopy(paquete.getData(), paquete.getOffset(), datos, 0, paquete.getLength());
        return new DatagramPacket(datos, datos.length, paquete.getAddress(), paquete.getPort());
    }
}

