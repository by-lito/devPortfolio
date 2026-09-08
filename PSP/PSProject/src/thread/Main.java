package thread;

import contador.Hilo;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new ThreadChild();
        hilo1.start();

        Thread hilo2 = new Thread(() ->{
                System.out.println("HIIIIII");
        });

        Runnable hilo3 = new Runnable() {
            @Override
            public void run() {

            }
        };

        hilo2.start();

        Thread hilo4 = new Thread() {
            public void run(){
                 System.out.println("HIIIIII 3");
             }
        };
        hilo4.start();

        Generico <String,Double> g = (s,d) -> System.out.println("\nPALABRA | " + s + "\nNUMERO | " + d);

        g.funcion1("NOOOO", 0.5);
    }
}
