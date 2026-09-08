package exchanger;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
// HERE WE CREATE THE EXCHANGER THAT WILL BE USED AND START BOTH THREADS WITH THE SAME EXCHANGER
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Ip(exchanger)).start();
        new Thread(new Mac(exchanger)).start();

    }

}
