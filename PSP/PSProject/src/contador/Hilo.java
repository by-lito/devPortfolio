package contador;

public class Hilo extends Thread{
    private int cuentaStart;
    private int cuentaEnd;

    public void setCuentaEnd(int cuentaEnd) {
        this.cuentaEnd = cuentaEnd;
    }
}
