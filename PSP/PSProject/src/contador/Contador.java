package contador;

public class Contador extends Thread{

    private int total;

    public synchronized void suma(int cuenta){
        total+=cuenta;
    }

    public int getTotal(){
        return this.total;
    }

}
