package pagemanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageManager extends Thread{

    private static List<String> mylist = Collections.synchronizedList(new ArrayList<>());
int i;
    @Override
    public void run() {

        while (true) {
            synchronized (mylist) {
            if (mylist.size()>=10) {
                mylist.remove(0);
            } else if (mylist.size()<10) {
                mylist.add(new String("texto"));
            }
            for (String string : mylist) {
                System.out.println(i++);
                System.out.println(string);
            }
        }
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10;i++) {
            mylist.add(new String("texto"));

        }
        for (int i=0;i<100;i++) {
            new PageManager().start();
        }
    }

}
