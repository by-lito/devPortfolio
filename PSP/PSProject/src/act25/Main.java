package act25;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            Thread t = new Worker("THREAD " + i);
            t.start();

        }
    }
}
