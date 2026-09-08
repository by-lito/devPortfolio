package contador;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int parcial = 100000000/20;

        Contador contador = new Contador();
        contador.start();

        Thread[] lista = new Thread[20];

        for (int i = 0; i < 10; i++){

                    lista[i]=(new Thread(new  Runnable(){

                        private int inicio;
                        private int fin;
                        public void run(){

                        }

                    }));

        }

        for (int i = 10; i < 20; i++){

            lista[i]=(new Hilo());

        }

        for (Thread t : lista){

            t.start();

        }

        contador.join();

        for (Thread t : lista){

        }


    }
}
