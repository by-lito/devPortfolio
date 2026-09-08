package exchanger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Exchanger;
// WE IMPLEMENT RUNNABLE SO WE CAN START THE THREAD AND IMPLEMENT RUN
public class Mac implements Runnable{
// WE GIVE A STARTING VALUE TO OUR STRING SO WE CAN LOCATE WHICH CLASS ARE WE USING
    private String mac = "MAC: ";
    private Process p;

    private Exchanger<String> exchanger = new Exchanger<>();
//WE GET THE EXCHANGER WE WANT TO USE AS A PARAMETER
    Mac(Exchanger<String> exchanger){
        this.exchanger = exchanger;
    }

    public void run() {

        try {
            //WE GET THE INFO WE NEED
            p = new ProcessBuilder("cmd", "/c", "ipconfig /all").redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("sica")) {
                    mac += line;
                }
            }
            //THIS IS THE MOST IMPORTANT PART  WE ASSIGN THE STRING THAT WILL BE EXCHANGED TO THE VARIABLE THAT WILL BE MODIFIED
            String result = exchanger.exchange(mac);
            //WE NOW PRINT THE VARIABLE SO WE CAN SEE THE EXCHANGE
            System.out.println("MAC CLASS RESULTED IN --> " + result);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
