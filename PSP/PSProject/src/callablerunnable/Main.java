package callablerunnable;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce a number");
        int num = sc.nextInt();


        try (ExecutorService exec = Executors.newSingleThreadExecutor()) {

            Future<Integer> fut;
            exec.execute(new ClaseRunnable(num));

            fut = exec.submit(new ClaseCallable(num));

            System.out.println("this number highest divisor is : " + fut.get());

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
