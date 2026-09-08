package processes;

import java.io.*;

public class Main {

    public static void main(String[] args) {
//WE CREATE THE PROCESSES
        Process p;
        Process pHost;
        // WE CREATE THE FILES AND CHECK IF THE DIR EXISTS, IF NOT WE CREATE IT
        File f;
        File dir = new File("result");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
// WE CREATE AND START THE HOSTNAME COMMAND PROCESS
            pHost = new ProcessBuilder("cmd", "/c", "hostname").redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(pHost.getInputStream()));
// WE NOW ASSIGN A NAME TO THE FILE DEPENDENT ON THE NAME OF OUR HOST WHICH WE READ FROM THE PREVIOUS PROCESS
            f = new File(dir, br.readLine() + ".txt");
// WE CHECK IF THE FILE EXISTS AND IF NOT WE CREATE IT
            if (!f.exists()) {
                f.createNewFile();
            }
// WE START NOW THE IPCONFIG PROCESS AND STABLISH A BUFFERED READER TO GET ITS INPUTSTREAM
            p = new ProcessBuilder("cmd", "/c", "ipconfig").redirectErrorStream(true).start();
            br = new BufferedReader(new InputStreamReader(p.getInputStream(), "CP850"));
            // WE NOW CREATE THE BUFFERED WRITER TO BE ABLE TO WRITE INSIDE OUR HOST.txt FILE
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
// WE CREATE THE VARIABLE THAT WILL ITERATE THROUGH THE RESULT OF THE COMMAND
            String line;
//NOW WE READ THE RESULTS AND WRITE THEM LINE BY LINE ONLY IF THE LINE IS THE ONE WE WANT
            while ((line = br.readLine()) != null) {
                if (line.contains("IPv4")) {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
            }
// WE FLUSH AND CLOSE SO THAT IT CAN WRITE THE LINES AND TO PREVENT PROBLEMS
            bw.flush();
            bw.close();
            br.close();
// WE WAIT FOR THE PROCESS TO FINISH
            p.waitFor();
            System.out.println("Process has finished succesfully");

// IF WE CATCH ANY KIND OF ERROR WE PRINT AN ERROR MESSAGE
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR Process has failed");
            throw new RuntimeException(e);
        }

    }

}
