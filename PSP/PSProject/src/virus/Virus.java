package virus;

import javax.swing.*;
import java.io.IOException;

public class Virus {

    public void Contagio() {
        Process p;
        try {
            for (int i = 1; i <= 20; i++) {

                int j = (int) (Math.random() * 3);

                switch (j) {
                    case 0:
                        p = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
                        Thread.sleep(500);
                        p.destroy();
                        break;
                    case 1:
                        p = new ProcessBuilder("notepad").redirectErrorStream(true).start();
                        Thread.sleep(100);
                        break;
                    case 2:
                        p = new ProcessBuilder("cmd", "/c", "start", "cmd").redirectErrorStream(true).start();
                        Thread.sleep(500);
                        new ProcessBuilder("taskkill", "/F", "/IM", "cmd.exe").start();
                        break;
                }
            }
            new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();

            JOptionPane.showMessageDialog(null, "ЗДЕСЬ ВАШ КОМПЬЮТЕР ВЫКЛЮЧАЕТСЯ );", "информация о смерти вашего ПК", JOptionPane.ERROR_MESSAGE);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
