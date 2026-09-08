package ticker;

import java.util.TimerTask;

public class Ticker extends TimerTask {

    @Override
    public void run() {
        System.out.println("tick");
    }

}

