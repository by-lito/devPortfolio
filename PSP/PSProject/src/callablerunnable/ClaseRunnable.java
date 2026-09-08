package callablerunnable;

public class ClaseRunnable implements Runnable {

    int num;
    boolean key = false;

    public ClaseRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {

        for (int i = num - 1; i > 1; i--) {
            if (num % i == 0) {
                key = true;
            }
        }

        if (key == true) {
            System.out.println("RUNNABLE: Not a prime number");
        }
        else {
            System.out.println("RUNNABLE: prime number");
        }

    }
}
