package callablerunnable;

import java.util.concurrent.Callable;

public class ClaseCallable implements Callable <Integer> {

    int num;
    boolean key = false;

    public ClaseCallable(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {

        for (int i = num - 1; i > 1 && !key; i--) {

            if (num % i == 0) {

                num = i;
                key = true;

            }

        }
        System.out.println("CALLABLE : Ended with number " + num);
        return num;
    }
}
