package ticker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.schedule(new Ticker(), 1000, 1000);

    }

    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

}
