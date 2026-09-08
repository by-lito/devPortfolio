package exchanger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Exchanger;

public class Ip implements Runnable{

    private String ip = "IP: ";
    private Process p;

    private Exchanger<String> exchanger = new Exchanger<>();

    Ip(Exchanger<String> exchanger){
        this.exchanger = exchanger;
    }

    public void run() {

            try {
                p = new ProcessBuilder("cmd", "/c", "ipconfig").redirectErrorStream(true).start();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("IPv4")) {
                        ip += line;
                    }
                }
                String result = exchanger.exchange(ip);
                System.out.println("IP CLASS RESULTED IN --> " + result);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }


    }
}
