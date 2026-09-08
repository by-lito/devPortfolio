package act25;

import java.util.Random;

public class Worker extends Thread {

    String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {

            try {

                int num = 1000 + new Random().nextInt(9000);
                Thread.sleep(num);
//I've added the time in ms so that we can check that they are executing at the same time in diff threads
                System.out.println( "Time of exe [" + System.currentTimeMillis() + "] Working" + name);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
