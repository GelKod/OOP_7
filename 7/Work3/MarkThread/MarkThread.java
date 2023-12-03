package Work3.MarkThread;

import Work3.Vehicle.Vehicle;

public class MarkThread implements Runnable{
    private Vehicle v;
    public MarkThread(Vehicle v){
        this.v = v;
    }
    @Override
    public void run() {
        System.out.println(v.getMake());
    }
}
